package br.com.basecmp.sisgaragem.model;

import br.com.basecmp.sisgaragem.model.enums.Finalidade;
import br.com.basecmp.sisgaragem.model.enums.StatusPedido;
import br.com.basecmp.sisgaragem.model.enums.TipoViatura;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "usuario_id")
	private Usuarios usuarioRemetente;

	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "motorista_id")
	private Motorista motoristaDoPedido;

	@ManyToOne
	@org.hibernate.annotations.ForeignKey(name = "viatura_id")
	private Viatura viaturaDoPedido;

	@NotBlank(message = "Informe o local de embarque")
	private String localDeEmbarque;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Informe a data de embarque")
	private LocalDate dataDeEmbarque;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Informe a data de chegada")
	private LocalDate dataDeChegada;

	@NotBlank(message = "Informe a hora de embarque")
	private String horaDoEmbarque;

	@NotBlank(message = "Informe a hora de chegada")
	private String horaDeChegada;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDoPedido;

	@NotNull(message = "Informe o tipo da viatura")
	private TipoViatura tipoDeViatura;

	@NotBlank(message = "Informe a descrição do pedido")
	private String missaoDescricao;

	@NotBlank(message = "Informe a quem o motorista deverá se apresentar")
	private String apresentacao;

	@NotBlank(message = "Informe o destino")
	private String destino;
	
	@NotBlank(message = "Informe o telefone de contato")
	private String contatoPedido;

	@NotBlank(message = "Informe o ramal")
	private String ramalPedido;

	private Finalidade finalidade;

	private String justificativaRejeicao;

	private Long quantidadePassageiros;

	private StatusPedido statusPedido;
}
