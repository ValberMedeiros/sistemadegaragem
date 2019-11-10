package br.com.basecmp.sisgaragem.service;

import br.com.basecmp.sisgaragem.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.model.Motorista;
import br.com.basecmp.sisgaragem.repository.MotoristaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroMotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Motorista> listar() {

        List<Motorista> motoristas = motoristaRepository.findAll();

        if (motoristas.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não há nenhum motorista cadastrado.")
            );
        } else {
            return motoristas;
        }
    }

    public Motorista buscar(Long id) {

        Optional<Motorista> motorista = motoristaRepository.findById(id);

        if (motorista.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar o motorista com o código %d.", id)
            );
        } else {
            return motorista.get();
        }
    }

    public Motorista salvar(Motorista motorista) {

        motorista = motoristaRepository.save(motorista);

        return motorista;
    }

    public Motorista atualizar(Long id, Motorista motorista) {

        Optional<Motorista> motoristaAtual = motoristaRepository.findById(id);

        if (motoristaAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um motorista com o código %d.", id)
            );
        } else {
            BeanUtils.copyProperties(motorista, motoristaAtual.get(), "id");
            Motorista motoristaAtualizado = salvar(motoristaAtual.get());
            return motoristaAtualizado;
        }
    }

    public void remover(Long id) {
        try {
            motoristaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um motorista com o código %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Não foi possível remover o motorista com código %d, pois o mesmo está em uso", id)
            );
        }
    }

}
