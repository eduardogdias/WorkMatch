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

import java.util.Date;

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

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    // construtor sem id
    public Vaga(String cargo, Date dataFim, int experiencia, NivelIngles nivelIngles, ModeloTrabalho modeloTrabalho, Formacao formacao, Estado estado, int match, Empresa empresa) {
        this.cargo = cargo;
        this.dataFim = dataFim;
        this.experiencia = experiencia;
        this.nivelIngles = nivelIngles;
        this.modeloTrabalho = modeloTrabalho;
        this.formacao = formacao;
        this.estado = estado;
        this.match = match;
        this.empresa = empresa;
    }
}
