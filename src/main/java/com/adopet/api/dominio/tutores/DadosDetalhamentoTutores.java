package com.adopet.api.dominio.tutores;

public record DadosDetalhamentoTutores(Long id, String nome, String email) {
    public DadosDetalhamentoTutores(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
