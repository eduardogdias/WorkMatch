package br.com.workmatchapi.workmatchapi.model.repository;

import br.com.workmatchapi.workmatchapi.model.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}
