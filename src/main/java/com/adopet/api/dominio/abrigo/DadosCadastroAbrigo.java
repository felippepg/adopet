package com.adopet.api.dominio.abrigo;

import com.adopet.api.dominio.endereco.DadosEndereco;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAbrigo(@NotBlank
                                  String nome,
                                  @NotBlank
                                  String email,
                                  @NotBlank
                                  String senha,
                                  @NotNull
                                  @JsonAlias("perfil_id")
                                  Long id,
                                  DadosEndereco endereco) {
}
