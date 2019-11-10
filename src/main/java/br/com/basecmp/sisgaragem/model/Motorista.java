package br.com.basecmp.sisgaragem.model;

import br.com.basecmp.sisgaragem.model.enums.StatusMotorista;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Motorista implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Informe a identidade")
	private String identidade;
	
	@ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "postoGraduacao_id")
    private PostoGraduacao postoGraduacaoMotorista;
	
	@NotBlank(message = "Informe o nome completo")
	private String nomeCompleto;
	
	@NotBlank(message = "Informe o nome de guerra")
	private String nomeDeGuerra;
	
	private StatusMotorista statusMotorista;

	@JsonIgnore
	@OneToMany(mappedBy = "motoristaDoPedido")
    private List<Pedido> pedidos;
	
	private String categoriaCNH;
}
