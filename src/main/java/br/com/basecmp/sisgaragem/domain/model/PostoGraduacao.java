package br.com.basecmp.sisgaragem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class PostoGraduacao implements Serializable {

    private static final long serialVersionUID = 1L;

    public PostoGraduacao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postoGraduacao_id;

    @NotBlank
    private String postoGrad;

    @JsonIgnore
    @OneToMany(mappedBy = "postoGraduacao")
    private List<Usuarios> usuarios;

    @JsonIgnore
    @OneToMany(mappedBy = "postoGraduacaoMotorista")
    private List<Motorista> motoristas;

}
