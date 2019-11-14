package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Role, Long> {
}
