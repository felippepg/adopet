package com.adopet.api.dominio.tutores;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarTutor(@NotBlank Long id, String nome, String email) {

}
