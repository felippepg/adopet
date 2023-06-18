package com.adopet.api.dominio.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarUsuario(@NotNull Long id, String nome, String email ) {

}
