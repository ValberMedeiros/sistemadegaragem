package br.com.basecmp.sisgaragem.domain.repository;

import br.com.basecmp.sisgaragem.domain.model.Motorista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
	List<Motorista> findByNomeCompletoIgnoreCaseContainingOrderByNomeCompleto(String pesquisa, Pageable pageable);

	@Query("select u from Motorista u where statusMotorista =0")
    List<Motorista> findAllStatusMotoristaProntos();

}
