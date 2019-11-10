package br.com.basecmp.sisgaragem.model;

import br.com.basecmp.sisgaragem.model.enums.Combustivel;
import br.com.basecmp.sisgaragem.model.enums.StatusViatura;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Viatura implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotBlank(message = "Informe a placa")
    private String placa;
	
	@NotNull(message = "Informe o renavan")
    private Long renavan;
	
	@NotBlank(message = "Informe o EB")
    private String EB;
	
	@NotBlank(message = "Informe o NEE")
    private String NEE;

	@NotBlank(message = "Informe o chassi")
    private String chassi;

	@NotBlank(message = "Informe o modelo")
	private String modelo;
	
    private Combustivel combustivel;

	@NotNull(message = "Informe a capacidade de passageiros")
    private Long capacidadePassageiros;

	@NotNull(message = "Informe a quantidade de portas")
    private Long quantidadeDePortas;

	@NotNull(message = "Informe a capacidade do tanque")
    private Long capacidadeDoTanque;

	@NotNull(message = "Informe a autonômia")
    private Long autonomia;

	@NotBlank(message = "Informe a finalidade de utilização do veículo")
    private String utilizacao;

	@JsonIgnore
	@OneToMany(mappedBy = "viaturaDoPedido")
	private List<Pedido> pedidos;
	
	private StatusViatura statusViatura;

}
