package desafio.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.rest.model.Pessoa;
import desafio.api.rest.model.Tarefa;
import desafio.api.rest.repository.TarefaRepository;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tarefa) {

		Tarefa tarefaSalva = tarefaRepository.save(tarefa);

		return new ResponseEntity<Tarefa>(tarefaSalva, HttpStatus.OK);

	}

	@PutMapping(value = "/alocar/{id}", produces = "application/json")
	public ResponseEntity<Tarefa> alocarPessoaNaTarefa(@PathVariable("id") Long tarefaId, @RequestBody Pessoa pessoaRequest) {

	    Tarefa tarefa = tarefaRepository.findById(tarefaId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (pessoaRequest.getIdDepartamento() != null && tarefa.getIdDepartamento() != null && 
        		pessoaRequest.getIdDepartamento().getId().equals(tarefa.getIdDepartamento().getId())) {
            tarefa.setIdPessoa(pessoaRequest);
            tarefaRepository.save(tarefa);
            return ResponseEntity.ok(tarefa);
        } else {
        	return ResponseEntity.badRequest().body(null);
        }
	}

	@PutMapping(value = "/finalizar/{id}", produces = "application/text")
	public String finalizarTarefa(@PathVariable("id") Long id) {

		Optional<Tarefa> optionalTarefa = tarefaRepository.findById(id);

		if (optionalTarefa.isPresent()) {
			Tarefa tarefa = optionalTarefa.get();
			tarefa.setFinalizado(true);

			tarefaRepository.save(tarefa);

			return "Tarefa com o ID " + id + " finalizada";
		} else {
			return "Tarefa não encontrada com o ID: " + id;
		}

	}

	@GetMapping(value = "/pendentes", produces = "application/json")
	public ResponseEntity<List<Tarefa>> listaTopTresTarefasAntigas() {
		Pageable pageable = PageRequest.of(0, 3);
		List<Tarefa> tarefas = (List<Tarefa>) tarefaRepository.listaTopTresTarefasAntigasPrazo(pageable);

		return new ResponseEntity<List<Tarefa>>(tarefas, HttpStatus.OK);
	}

}
