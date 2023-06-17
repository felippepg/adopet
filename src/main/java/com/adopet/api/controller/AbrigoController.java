package com.adopet.api.controller;

import com.adopet.api.config.NotFoundException;
import com.adopet.api.dominio.abrigo.Abrigo;
import com.adopet.api.dominio.abrigo.AbrigoRepository;
import com.adopet.api.dominio.abrigo.DadosAtualizarAbrigo;
import com.adopet.api.dominio.abrigo.DadosDetalhamentoAbrigo;
import com.adopet.api.dominio.endereco.DadosEndereco;
import com.adopet.api.dominio.perfil.PerfilRepository;
import com.adopet.api.dominio.usuario.DadosAtualizarUsuario;
import com.adopet.api.dominio.tutores.DadosDetalhamentoTutores;
import com.adopet.api.dominio.usuario.DadosCadastroUsuario;
import com.adopet.api.dominio.usuario.Usuario;
import com.adopet.api.dominio.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("abrigos")
public class AbrigoController {

    @Autowired
    AbrigoRepository abrigoRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarAbrigo(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        var perfil = perfilRepository.getReferenceById(dados.id());
        var usuario = usuarioRepository.save(new Usuario(dados, perfil));
        var abrigo = new Abrigo(dados.endereco(), usuario);
        abrigoRepository.save(abrigo);

        var uri = uriComponentsBuilder.path("abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));

    }

    @GetMapping
    public ResponseEntity buscarTodosOsAbrigos(Pageable pageable) {
        var abrigos = abrigoRepository.findAll(pageable);

        if(abrigos.isEmpty()) {
            throw new NotFoundException("Abrigo não encontrado");
        } else {
            return ResponseEntity.ok(abrigos.map(abrigo -> new DadosDetalhamentoAbrigo(abrigo)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAbrigo> buscarAbrigoPorId(@PathVariable Long id) {
        var abrigo = abrigoRepository.findById(id);
        if(abrigo.isEmpty()) {
            throw new NotFoundException("Abrigo não encontrado");
        }
        return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigo.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarAbrigo(@PathVariable Long id) {
        var abrigo = abrigoRepository.findById(id);
        if(abrigo.isEmpty()) {
            throw new NotFoundException("Abrigo não encontrado, verifique o ID");
        } else {
            abrigoRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody  @Valid DadosAtualizarAbrigo dados) {
        var abrigo = abrigoRepository.findById(dados.id());
        if(abrigo.isEmpty()) {
            throw new NotFoundException("Abrigo não encontrado");
        }

        var usuario = usuarioRepository.getReferenceById(abrigo.get().getUsuario().getId());

        usuario.atualizar(new DadosAtualizarUsuario(usuario.getId(), dados.nome(), dados.email()));
        abrigo.get().getEndereco().atualizarDadosEndereco(dados.endereco());

        return ResponseEntity.ok(new DadosDetalhamentoAbrigo(abrigo.get()));
    }
}
