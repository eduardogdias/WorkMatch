package br.com.workmatchapi.workmatchapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="TB_CANDIDATURA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "curriculo_id")
    private Curriculo curriculo;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_candidatura")
    private Date dataCandidatura;

    public Candidatura(Vaga vaga, Curriculo curriculo, Date dataCandidatura) {
        this.vaga = vaga;
        this.curriculo = curriculo;
        this.dataCandidatura = dataCandidatura;
    }

    public Candidatura(Vaga vaga, Curriculo curriculo) {
        this.vaga = vaga;
        this.curriculo = curriculo;
    }
}
