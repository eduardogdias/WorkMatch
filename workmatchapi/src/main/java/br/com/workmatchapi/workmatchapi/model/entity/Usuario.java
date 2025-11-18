package br.com.workmatchapi.workmatchapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @Column(length = 11)
    private String telefone;

    @Column(length = 11, unique = true)
    private String cpf;

    private String senha;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Curriculo curriculo;

    public Usuario(String nome, String email, String telefone, String cpf, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.senha = senha;
    }
}
