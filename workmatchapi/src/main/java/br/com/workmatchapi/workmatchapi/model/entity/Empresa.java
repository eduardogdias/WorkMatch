package br.com.workmatchapi.workmatchapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_EMPRESA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 14, unique = true)
    private String cnpj;

    private String email;

    @Column(length = 11)
    private String telefone;

    public Empresa(String nome, String cnpj, String email, String telefone) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.telefone = telefone;
    }
}
