package br.com.workmatchapi.workmatchapi.model.entity;

import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    @CollectionTable(
            name = "curriculo_skills",
            joinColumns = @JoinColumn(name = "curriculo_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Curriculo(Formacao formacao, int experiencia, NivelIngles nivelIngles, Estado estado, List<String> skills, Usuario usuario) {
        this.formacao = formacao;
        this.experiencia = experiencia;
        this.nivelIngles = nivelIngles;
        this.estado = estado;
        this.skills = skills;
        this.usuario = usuario;
    }
}
