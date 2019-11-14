package br.com.basecmp.sisgaragem.api.controller;

import br.com.basecmp.sisgaragem.domain.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.domain.model.Viatura;
import br.com.basecmp.sisgaragem.domain.service.CadastroViaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaturas")
public class ViaturaController {

	@Autowired
    private CadastroViaturaService cadastroViatura;

    /**
     * Método responsável por fornecer uma lista de viaturas
     * @return List<Viaturas> de todas as viaturas salvas na base de dados
     */
    @GetMapping
    public ResponseEntity<?> listar(){

        /**
         * Se não houver viatura cadastrada no banco retornará NO CONTENT, se não, retornará uma lista de viaturas
         */
        try {
            List<Viatura> viaturas = cadastroViatura.listar();
            return ResponseEntity.status(HttpStatus.OK).body(viaturas);
        } catch (EntidadeVaziaException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /**
     * Metodo responsável por fornecer uma viatura com o Id passado
     * @param id id da viatura a ser buscada
     * @return Viatura buscada
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        /**
         * Se for encontrada a viatura com o id passado retornará a viatura, se não, retornará NOT FOUND
         */
        try {
            Viatura viatura = cadastroViatura.buscar(id);
            return ResponseEntity.status(HttpStatus.OK).body(viatura);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por salvar uma viatura na base de dados
     * @param viatura viatura a ser salva na base de dados
     * @return Viatura salva no banco com sua respectiva URI
     */
    @PostMapping
    public ResponseEntity<Viatura> salvar(@RequestBody Viatura viatura){
        viatura = cadastroViatura.salvar(viatura);

        return ResponseEntity.status(HttpStatus.CREATED).body(viatura);
    }

    /**
     * Médodo responsável por atualizar uma viatura na base de dados
     * @param id id da viatura a ser atualizada
     * @param viatura dados atualizados da viatura
     * @return Viatura ja atualizada na base de dados
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Viatura viatura) {

        /**
         * Se for encontrada a viatura com o id passado retornará a viatura com os dados atualizados, se não, retornará
         * NOT FOUND
         */
        try {
            viatura = cadastroViatura.editar(id, viatura);
            return ResponseEntity.status(HttpStatus.OK).body(viatura);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por deletar uma viatura na base de dados
     * @param id id da viatura a ser deletada
     * @return Status da transação no banco
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        /**
         * Se for encontrada a viatura com o id passado retornará NO CONTENT (viatura excluída), se não, retornará
         * NOT FOUND caso a viatura não exista ou CONFLICT caso a viatura esteja sendo usada
         */
        try {
            cadastroViatura.remover(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
