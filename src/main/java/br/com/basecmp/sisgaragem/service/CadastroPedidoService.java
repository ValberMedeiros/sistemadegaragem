package br.com.basecmp.sisgaragem.service;

import br.com.basecmp.sisgaragem.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.model.Pedido;
import br.com.basecmp.sisgaragem.repository.PedidoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();

        if (pedidos.isEmpty()) {
            throw new EntidadeVaziaException(
                    String.format("Não há nenhum pedido cadastrado.")
            );
        } else {
            return pedidos;
        }
    }

    public Pedido buscar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (pedido.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar o pedido com o código %d.", id)
            );
        } else {
            return pedido.get();
        }
    }

    public Pedido salvar(Pedido pedido) {
        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public Pedido atualizar(Long id, Pedido pedido) {
        Optional<Pedido> pedidoAtual = pedidoRepository.findById(id);

        if (pedidoAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um pedido com o código %d.", id)
            );
        } else {
            BeanUtils.copyProperties(pedido, pedidoAtual.get(), "id");
            Pedido pedidoAtualizado = salvar(pedidoAtual.get());
            return pedidoAtualizado;
        }
    }

    public void remover(Long id) {
        try {
            pedidoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um pedido com o código %d.", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Não foi possível remover o pedido com o código %d, pois ele está em uso.", pedidoRepository)
            );
        }
    }

}
