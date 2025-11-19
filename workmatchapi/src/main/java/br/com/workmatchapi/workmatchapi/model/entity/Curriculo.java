package br.com.workmatchapi.workmatchapi.model.entity;

import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TB_CURRICULO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Formacao formacao;

    private int experiencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_ingles")
    private NivelIngles nivelIngles;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Curriculo(Formacao formacao, int experiencia, NivelIngles nivelIngles, Estado estado, Usuario usuario) {
        this.formacao = formacao;
        this.experiencia = experiencia;
        this.nivelIngles = nivelIngles;
        this.estado = estado;
        this.usuario = usuario;
    }
}
