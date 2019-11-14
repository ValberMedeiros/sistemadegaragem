package br.com.basecmp.sisgaragem.api.controller;

import br.com.basecmp.sisgaragem.domain.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.domain.model.Pedido;
import br.com.basecmp.sisgaragem.domain.repository.PedidoRepository;
import br.com.basecmp.sisgaragem.domain.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedido;

    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Método responsável por fornecer uma lista (Com possibilidade de filtros) de todos os pedidos cadastrados na base de dados
     * @param nome caso seja fornecido um nome será retornado todos os pedidos com o nome informado
     * @param dataInicial caso seja fornecido um nome será retornado todos os pedidos com datas de embarque a partir dessa data
     * @param dataFinal caso seja fornecido um nome será retornado todos os pedidos com datas de embarque até essa data
     * @return List<Pedido> contendo todos os pedidos cadastrados no banco
     */
    @GetMapping
    public ResponseEntity<?> listar(String nome, String dataInicial, String dataFinal) {

        /**
         * Se hover pedidos cadastrador na base de dados retornará uma lista contendo todos os pedidos, se não,
         * retornará NO CONTENT
         */
        try {
            List<Pedido> pedidos = cadastroPedido.listar(nome, dataInicial, dataFinal);
            return ResponseEntity.status(HttpStatus.OK).body(pedidos);
        } catch (EntidadeVaziaException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /**
     * Método responsável por fornecer um pedido com o id informado
     * @param id id do pedido a ser buscado na base de dados
     * @return Pedido buscado na base de dados com o id informado
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        /**
         * Se houver o pedido na base de dados com o id informando será retornado o pedido, se não. será retornado
         * NOT FOUND
         */
        try {
            Pedido pedido = cadastroPedido.buscar(id);
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por salvar na base de dados um pedido
     * @param pedido pedido a ser salo na base de dados
     * @return pedido ja salvo na base de dados
     */
    @PostMapping
    public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
        pedido = cadastroPedido.salvar(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    /**
     * Método responsável por atualizar um pedido na base de dados com o id informado
     * @param id id do pedido a ser atualizar
     * @param pedido dados do pedido a ser atualizado na base de dados
     * @return pedido ja com os dados atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {

        /**
         * Se houver o pedido na base de dados com o id informado será retornado o pedido ja atualizado, se não,
         * será retornado NOT FOUND
         */
        try {
            pedido = cadastroPedido.atualizar(id, pedido);
            return ResponseEntity.status(HttpStatus.OK).body(pedido);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por remover um pedido da base de dados com o id informado
     * @param id id do pedido a ser removido da base de dados
     * @return se houver o pedido na base de dados com o id informado será retornado NO CONTENT, se não, será retornado
     * NOT FOUND caso não haja o pedido na base de dados ou CONFLICT caso o pedido esteja em uso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        /**
         * se houver o pedido na base de dados com o id informado será retornado NO CONTENT, se não, será retornado
         * NOT FOUND caso não haja o pedido na base de dados ou CONFLICT caso o pedido esteja em uso
         */
        try {
            cadastroPedido.remover(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


}
