package com.adopet.api.controller;

import com.adopet.api.config.NotFoundException;
import com.adopet.api.dominio.perfil.PerfilRepository;
import com.adopet.api.dominio.tutores.*;
import com.adopet.api.dominio.usuario.DadosAtualizarUsuario;
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
@RequestMapping("tutores")
public class TutorController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTutores(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder) {
        var perfil = perfilRepository.getReferenceById(dados.id());
        var usuario = new Usuario(dados, perfil);
        usuarioRepository.save(usuario);
        var uri = uriComponentsBuilder.path("tutores/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTutores(usuario));
    }

    @GetMapping
    public ResponseEntity buscarTodosTutores(Pageable pageable) {
        var usuarios = usuarioRepository.findAllByTutor(pageable);

        if(usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(usuarios.map(usuario -> new DadosDetalhamentoTutores(usuario)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTutores> buscarTutorPorId(@PathVariable Long id) {
        var usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()) {
            throw new NotFoundException("Tutor não encontrado");
        }
        return ResponseEntity.ok(new DadosDetalhamentoTutores(usuario.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleterTutor(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody  @Valid DadosAtualizarUsuario dados) {
        var usuario = usuarioRepository.findById(dados.id());
        if(usuario.isEmpty()) {
            throw new NotFoundException("Tutor não encontrado");
        }
        usuario.get().atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTutores(usuario.get()));
    }

}
