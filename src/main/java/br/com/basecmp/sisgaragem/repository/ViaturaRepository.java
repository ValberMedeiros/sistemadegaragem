package br.com.basecmp.sisgaragem.repository;

import br.com.basecmp.sisgaragem.model.Viatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViaturaRepository extends JpaRepository<Viatura, Long> {
	
	List<Viatura> findByPlacaIgnoreCaseContainingOrderByPlaca(String pesquisa);

	@Query("select u from Viatura u where statusViatura =2")
    List<Viatura> findViaturaProntas();
	
}
