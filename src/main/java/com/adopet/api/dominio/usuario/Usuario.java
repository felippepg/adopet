package com.adopet.api.dominio.usuario;

import com.adopet.api.dominio.perfil.Perfil;
import com.adopet.api.dominio.tutores.DadosAtualizarTutor;
import com.adopet.api.dominio.tutores.DadosCadastroTutores;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity(name = "Usuario")
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String senha;

    @OneToOne
    private Perfil perfil;

    public Usuario(DadosCadastroTutores dados, Perfil perfil) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.perfil = perfil;
    }

    public void atualizar(DadosAtualizarTutor dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
    }
}
