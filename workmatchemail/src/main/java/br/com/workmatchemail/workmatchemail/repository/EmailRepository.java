package br.com.workmatchemail.workmatchemail.repository;

import br.com.workmatchemail.workmatchemail.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}

