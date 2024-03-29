package br.com.basecmp.sisgaragem.domain.service;

import br.com.basecmp.sisgaragem.domain.exception.EntidadeEmUsoException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeNaoEncontradaException;
import br.com.basecmp.sisgaragem.domain.exception.EntidadeVaziaException;
import br.com.basecmp.sisgaragem.domain.model.PostoGraduacao;
import br.com.basecmp.sisgaragem.domain.model.Role;
import br.com.basecmp.sisgaragem.domain.model.Usuarios;
import br.com.basecmp.sisgaragem.domain.repository.PostoGraduacaoRepository;
import br.com.basecmp.sisgaragem.domain.repository.RolesRepository;
import br.com.basecmp.sisgaragem.domain.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostoGraduacaoRepository postoGraduacaoRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public List<Usuarios> listar() {
        List<Usuarios> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, "PostoGraduacao"));

        if (usuarios.isEmpty()) {
            throw new EntidadeVaziaException(
                    String.format("Não há nenhum usuario cadastrado.")
            );
        } else {
            return usuarios;
        }
    }

    public Usuarios buscar(Long id) {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um usuário com o código %d.", id)
            );
        } else {
            return usuario.get();
        }
    }

    public Usuarios salvar(Usuarios usuario) {
        try {
            usuario = usuarioRepository.save(usuario);
            return usuario;
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("O nome de usuário iformado já está sendo usado.")
            );
        }
    }

    public Usuarios atualizar(Long id, Usuarios usuario) {

        Optional<Usuarios> usuarioAtual = usuarioRepository.findById(id);

        if (usuarioAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um usuário com o código %d.", id)
            );
        } else {
            BeanUtils.copyProperties(usuario, usuarioAtual.get());
            Usuarios usuarioAtualizado = salvar(usuario);

            return usuarioAtualizado;
        }
    }

    public void remover(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não foi possível encontrar um usuário com o código %d.", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Não foi possível remover o usuario com o código %d, pois ele esta em uso.", id)
            );
        }
    }

    public List<Role> getRoles() {
        List<Role> roles = this.rolesRepository.findAll();

        if (roles.isEmpty()) {
            throw new EntidadeVaziaException(
                    String.format("Não existe nenhuma role cadastrada")
            );
        } else {
            return roles;
        }
    }

    public List<PostoGraduacao> getPostoGraduacao() {
        return postoGraduacaoRepository.findAll();
    }

}
