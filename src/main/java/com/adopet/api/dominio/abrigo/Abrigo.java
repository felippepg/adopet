package com.adopet.api.dominio.abrigo;

import com.adopet.api.dominio.endereco.DadosEndereco;
import com.adopet.api.dominio.endereco.Endereco;
import com.adopet.api.dominio.usuario.DadosCadastroUsuario;
import com.adopet.api.dominio.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Abrigo")
@Table(name = "abrigo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Abrigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Endereco endereco;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Abrigo(DadosEndereco dados, Usuario usuario) {
        this.endereco = new Endereco(dados);
        this.usuario = usuario;
    }
}
