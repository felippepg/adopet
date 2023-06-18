package com.adopet.api.dominio.tutores;

import com.adopet.api.dominio.usuario.Usuario;

public record DadosDetalhamentoTutores(Long id, String nome, String email) {
    public DadosDetalhamentoTutores(Usuario tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getEmail());
    }
}
