package br.com.basecmp.sisgaragem.api.controller;

import br.com.basecmp.sisgaragem.domain.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.domain.model.Role;
import br.com.basecmp.sisgaragem.domain.model.Usuarios;
import br.com.basecmp.sisgaragem.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService cadastroUsuario;

    /**
     * Método responsável por fornecer uma lista com todos os usuários cadastrados na base de dados
     * @return List<Usuario> com todos os usuários cadastrados na base de dados
     */
    @GetMapping
    public ResponseEntity<?> listar() {

        /**
         * Se houver usuários cadastrados na base de dados, será retornado uma lista com todos os usuários, se não,
         * será retornado NO CONTENT
         */
        try {
            List<Usuarios> usuarios = cadastroUsuario.listar();
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        } catch (EntidadeVaziaException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /**
     * Método responsável por buscar um usuario na base de dados com o id informado
     * @param id id do usuário a ser buscado na base de dados
     * @return Usuario buscado ou status NOT FOUND
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        /**
         * Se houver um usuário com o código informado será retornado o usuario, se não, será retornado NOT FOUND
         */
        try {
            Usuarios usuario = cadastroUsuario.buscar(id);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por cadastrar um usuario na base de dados
     * @param usuario dados do usuário a ser cadastrado na base de dados
     * @return Usuario ja cadastrado na base de dados
     */
    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Usuarios usuario) {

        /**
         * Se não houver nenhum usuário com o nome de usuário informado será retornado o usuario cadastrado, se não,
         * será retornado CONFLICT
         */
        try {
            usuario = cadastroUsuario.salvar(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Método reposável por atualizar um usuario na base de dados com o id informado
     * @param id id do usuário a ser atualizado
     * @param usuario dados atualizados do usuario
     * @return Usuario com os dados ja atualizados
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuarios usuario) {

        /**
         * Se houver um usuário na base de dados com o id informado será retornado o usuario com os dados ja atualizados
         * se não, será retornado NOT FOUND*/
        try {
            usuario = cadastroUsuario.atualizar(id, usuario);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Método responsável por remover um usuario da base de dados com o id informado
     * @param id id do usuário a ser removido da base de dados
     * @return status da transação na base de dados
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {

        /**
         * Se houver um usuário na base de dados com o id informado será retornado NO CONTENT, se não, será retornado
         * NOT FOUND caso nao seja encontrado e CONFLICT caso esteja em uso
         */
        try {
            cadastroUsuario.remover(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /**
     * Método responsável por fornecer uma lista de roles da base de dadps
     * @return List de roles da base de dados
     */
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        /**
         * Se houver roles cadastradas na base de dados será retornada uma lista com as roles, se não, será retornado
         * NO CONTENT
         */
        try {
            List<Role> roles = this.cadastroUsuario.getRoles();
            return ResponseEntity.status(HttpStatus.OK).body(roles);
        } catch (EntidadeVaziaException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

}
