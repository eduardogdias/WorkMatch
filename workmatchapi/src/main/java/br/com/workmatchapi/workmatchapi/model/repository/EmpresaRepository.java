package br.com.workmatchapi.workmatchapi.model.repository;

import br.com.workmatchapi.workmatchapi.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
