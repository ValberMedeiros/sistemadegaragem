package br.com.basecmp.sisgaragem.controller;

import br.com.basecmp.sisgaragem.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.model.Motorista;
import br.com.basecmp.sisgaragem.service.CadastroMotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired
    private CadastroMotoristaService cadastroMotorista;

    /**
     * Método responsável por fornecer uma lista de motoristas
     * @return List<Motoristas> com todos os motoristas cadastrados
     */
    @GetMapping
    public ResponseEntity<?> listar() {

        /**
         * Se não houver motoristas cadastrados retornará NO CONTENT, se não, retornará lista de todos os motoristas
         * cadastrados
         */
        try {
            List<Motorista> motoristas = cadastroMotorista.listar();
            return ResponseEntity.status(HttpStatus.OK).body(motoristas);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /**
     * Método responsável por fornecer um motorista com o id informado
     * @param id id do motorista a ser buscado na base de dados
     * @return Motorista buscado na base de dados com o id informado
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        /**
         * Se não houver um motorista com o id informado será retornado NOT FOUND, se não, será retornado o motorista
         * com o id informado
         */
        try {
            Motorista motorista = cadastroMotorista.buscar(id);
            return ResponseEntity.status(HttpStatus.OK).body(motorista);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por salvar um motorista na base de dados
     * @param motorista motorista a ser salvo na base de dados
     * @return Motorista salvo na base de dados com sua respectiva URI
     */
    @PostMapping()
    public ResponseEntity<Motorista> salvar(@RequestBody Motorista motorista) {

        motorista = cadastroMotorista.salvar(motorista);

        return ResponseEntity.status(HttpStatus.CREATED).body(motorista);
    }

    /**
     * Método responsável por atualizar um motorista da base de dados com o id informado
     * @param id id do motorista a ser atualizado da base de dados
     * @param motorista dados a serem atualizar do motorista na base de dados
     * @return Motorista com os dados ja atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Motorista motorista) {

        /**
         * Se o motorista com o id informado for encontrado será retornado o motorista com os dados atualizados,
         * se não, será retornado NOT FOUND
         */
        try {
            motorista = cadastroMotorista.atualizar(id, motorista);
            return ResponseEntity.status(HttpStatus.OK).body(motorista);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por remover um motorista da base de dados com o id informado
     * @param id id do motorista a ser removido da base de dados
     * @return status da transação do banco de dados
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cadastroMotorista.remover(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
