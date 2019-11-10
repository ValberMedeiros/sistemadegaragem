package br.com.basecmp.sisgaragem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class PostoGraduacao implements Serializable {

    private static final long serialVersionUID = 1L;

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
