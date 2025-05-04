package desafio.api.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String titulo;

	private String descricao;

	private LocalDate prazo;
	
	private int duracao;

	private boolean finalizado;
    
	@ManyToOne
    @JoinColumn(name = "idDepartamento")
    private Departamento idDepartamento;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "idPessoa")
    private Pessoa idPessoa;

}
