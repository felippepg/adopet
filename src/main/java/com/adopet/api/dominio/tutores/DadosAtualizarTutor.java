package com.adopet.api.dominio.tutores;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTutor(@NotNull Long id, String nome, String email) {

}
