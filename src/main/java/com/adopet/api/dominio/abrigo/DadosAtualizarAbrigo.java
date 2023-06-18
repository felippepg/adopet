package com.adopet.api.dominio.abrigo;

import com.adopet.api.dominio.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAbrigo(@NotNull Long id, String nome, String email, DadosEndereco endereco) {
}
