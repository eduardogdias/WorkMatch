package br.com.workmatchapi.workmatchapi.model.entity;

import br.com.workmatchapi.workmatchapi.model.enums.Estado;
import br.com.workmatchapi.workmatchapi.model.enums.Formacao;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import br.com.workmatchapi.workmatchapi.model.enums.NivelIngles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="TB_VAGA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @Column(columnDefinition = "VARCHAR(MAX)")
    private String descricao;

    private Double salario;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFim;

    private int experiencia;

    @Column(name = "nivel_ingles")
    @Enumerated(EnumType.STRING)
    private NivelIngles nivelIngles;

    @Column(name = "modelo_trabalho")
    @Enumerated(EnumType.STRING)
    private ModeloTrabalho modeloTrabalho;

    @Enumerated(EnumType.STRING)
    private Formacao formacao;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private int match;

    @ElementCollection
    @CollectionTable(
            name = "vaga_skills",
            joinColumns = @JoinColumn(name = "vaga_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // construtor sem id

    public Vaga(String cargo, String descricao, Double salario, Date dataFim, int experiencia, NivelIngles nivelIngles, ModeloTrabalho modeloTrabalho, Formacao formacao, Estado estado, int match, List<String> skills, Empresa empresa) {
        this.cargo = cargo;
        this.descricao = descricao;
        this.salario = salario;
        this.dataFim = dataFim;
        this.experiencia = experiencia;
        this.nivelIngles = nivelIngles;
        this.modeloTrabalho = modeloTrabalho;
        this.formacao = formacao;
        this.estado = estado;
        this.match = match;
        this.skills = skills;
        this.empresa = empresa;
    }
}
