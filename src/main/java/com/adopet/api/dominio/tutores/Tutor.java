package com.adopet.api.dominio.tutores;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity(name = "Tutor")
@Table(name = "tutores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Tutor(DadosCadastroTutores dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
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
