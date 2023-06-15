package com.adopet.api.controller;

import com.adopet.api.config.NotFoundException;
import com.adopet.api.dominio.tutores.*;
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
    TutorRepository tutorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTutores(@RequestBody @Valid DadosCadastroTutores dados, UriComponentsBuilder uriComponentsBuilder) {
        var tutor = new Tutor(dados);
        tutorRepository.save(tutor);
        var uri = uriComponentsBuilder.path("tutores/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTutores(tutor));
    }

    @GetMapping
    public ResponseEntity buscarTodosTutores(Pageable pageable) {
        var tutores = tutorRepository.findAll(pageable);

        if(tutores.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(tutores.map(tutor -> new DadosDetalhamentoTutores(tutor)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTutores> buscarTutorPorId(@PathVariable Long id) {
        var tutor = tutorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTutores(tutor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleterTutor(@PathVariable Long id) {
        tutorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody  @Valid DadosAtualizarTutor dados) {
        var tutor = tutorRepository.findById(dados.id());
        if(tutor.isEmpty()) {
            throw new NotFoundException("Tutor não encontrado");
        }
        tutor.get().atualizar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTutores(tutor.get()));
    }

}
