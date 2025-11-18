package br.com.workmatchapi.workmatchapi.model.repository;

import br.com.workmatchapi.workmatchapi.model.entity.Vaga;
import br.com.workmatchapi.workmatchapi.model.enums.ModeloTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByModeloTrabalho(ModeloTrabalho modeloTrabalho);
}

