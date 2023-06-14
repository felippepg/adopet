package com.adopet.api.controller;

import com.adopet.api.dominio.tutores.DadosCadastroTutores;
import com.adopet.api.dominio.tutores.DadosDetalhamentoTutores;
import com.adopet.api.dominio.tutores.Tutor;
import com.adopet.api.dominio.tutores.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("tutores")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTutores> cadastrarTutores(@RequestBody @Valid DadosCadastroTutores dados) {
        var tutor = new Tutor(dados);
        tutorRepository.save(tutor);
        return ResponseEntity.ok(new DadosDetalhamentoTutores(tutor));
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

}
