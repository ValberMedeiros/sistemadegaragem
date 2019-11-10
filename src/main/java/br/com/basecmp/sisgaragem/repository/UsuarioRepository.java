package br.com.basecmp.sisgaragem.repository;

import br.com.basecmp.sisgaragem.model.Usuarios;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuarios, Long> {
    Usuarios findByusername(String username);
    
    List<Usuarios> findByNomeCompletoIgnoreCaseContainingOrderByNomeCompleto(String pesquisa);
}
