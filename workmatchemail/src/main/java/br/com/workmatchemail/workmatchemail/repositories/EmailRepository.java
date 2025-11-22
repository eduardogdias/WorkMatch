package br.com.workmatchemail.workmatchemail.repositories;

import br.com.workmatchemail.workmatchemail.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
