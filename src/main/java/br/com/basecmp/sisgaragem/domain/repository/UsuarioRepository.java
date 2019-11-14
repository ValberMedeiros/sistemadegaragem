package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository  extends JpaRepository<Usuarios, Long> {

}
