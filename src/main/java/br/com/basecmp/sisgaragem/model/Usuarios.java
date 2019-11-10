package br.com.basecmp.sisgaragem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Informe a identidade")
    private String identidade;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "postoGraduacao_id")
    private PostoGraduacao postoGraduacao;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_role",
    	joinColumns = @JoinColumn(name = "usuario_id",
    				  referencedColumnName = "id",
    				  table = "usuarios"),
    	inverseJoinColumns = @JoinColumn(name = "role_id",
    				  referencedColumnName = "id",
    				  table = "role"))
    private List<Role> roles;

    @NotBlank(message = "Informe o nome completo")
    private String nomeCompleto;

    @NotBlank(message = "Informe o nome de guerra")
    private String nomeDeGuerra;

    @NotBlank(message = "Informe o nome de usuário")
    private String username;

    @NotBlank(message = "Informe uma senha")
    private String password;
    
    @OneToMany(mappedBy = "usuarioRemetente")
    private List<Pedido> pedidos;

}
