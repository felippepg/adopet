package com.adopet.api.dominio.tutores;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTutores(
        @NotBlank
        String nome,
        @NotBlank
        String email,
        @NotBlank
        String senha

) {
}




