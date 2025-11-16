package br.com.workmatchapi.workmatchapi.model.repository;

import br.com.workmatchapi.workmatchapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
