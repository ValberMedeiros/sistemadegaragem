package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
