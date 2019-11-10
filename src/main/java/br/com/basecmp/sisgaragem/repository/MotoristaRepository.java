package br.com.basecmp.sisgaragem.repository;

import br.com.basecmp.sisgaragem.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
	List<Motorista> findByNomeCompletoIgnoreCaseContainingOrderByNomeCompleto(String pesquisa);

	@Query("select u from Motorista u where statusMotorista =0")
    List<Motorista> findAllStatusMotoristaProntos();
}
