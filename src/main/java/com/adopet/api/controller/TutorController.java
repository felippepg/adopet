package com.adopet.api.controller;

import com.adopet.api.dominio.tutores.DadosCadastroTutores;
import com.adopet.api.dominio.tutores.DadosDetalhamentoTutores;
import com.adopet.api.dominio.tutores.Tutor;
import com.adopet.api.dominio.tutores.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tutores")
public class TutorController {

    @Autowired
    TutorRepository tutorRepository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTutores> cadastrarTutores(@RequestBody DadosCadastroTutores dados) {
        var tutor = new Tutor(dados);
        tutorRepository.save(tutor);
        return ResponseEntity.ok(new DadosDetalhamentoTutores(tutor));
    }
}
