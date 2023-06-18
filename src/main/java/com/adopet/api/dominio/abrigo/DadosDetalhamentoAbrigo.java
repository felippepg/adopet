package com.adopet.api.dominio.abrigo;

public record DadosDetalhamentoAbrigo(Long id, String nome, String bairro, String cidade) {
    public DadosDetalhamentoAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getUsuario().getNome(), abrigo.getEndereco().getBairro(), abrigo.getEndereco().getCidade());
    }
}
