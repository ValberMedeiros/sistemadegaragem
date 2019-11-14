package br.com.basecmp.sisgaragem.domain.service;

import br.com.basecmp.sisgaragem.domain.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.domain.model.Viatura;
import br.com.basecmp.sisgaragem.domain.repository.ViaturaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroViaturaService {

    @Autowired
    private ViaturaRepository viaturaRepository;

    public Viatura salvar(Viatura viatura) {
        viatura = viaturaRepository.save(viatura);

        return viatura;
    }

    public Viatura editar(Long id, Viatura viatura) {
        Optional<Viatura> viaturaAtual = viaturaRepository.findById(id);

        if(viaturaAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar uma viatura com o código %d.", id)
            );
        } else {
            BeanUtils.copyProperties(viatura, viaturaAtual.get(), "id");
            Viatura viaturaAtualizada = viaturaRepository.save(viaturaAtual.get());
            return viaturaAtualizada;
        }
    }

    public List<Viatura> listar() {
        List<Viatura> viaturas = viaturaRepository.findAll();

        if(viaturas.isEmpty()) {
            throw new EntidadeVaziaException(
                    String.format("Não há nenhuma viatura cadastrada.")
            );
        } else {
            return viaturas;
        }
    }

    public Viatura buscar(Long id) {
        Optional<Viatura> viatura = viaturaRepository.findById(id);

        if(viatura.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar uma viatura com o código %d.", id)
            );
        } else {
            return viatura.get();
        }
    }

    public void remover(Long id) {
        try {
            viaturaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar uma viatura com o código %d.", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Não foi possível remover a viatura com o código %d, pois está em uso.", id)
            );
        }
    }

}
