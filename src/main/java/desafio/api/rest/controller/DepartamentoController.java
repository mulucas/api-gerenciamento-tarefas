package desafio.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.api.rest.model.Departamento;
import desafio.api.rest.repository.DepartamentoRepository;
import desafio.api.rest.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Departamento> adicionarDepartamento(@RequestBody Departamento departamento) {

		Departamento departamentoSalva = departamentoRepository.save(departamento);

		return new ResponseEntity<Departamento>(departamentoSalva, HttpStatus.OK);

	}

	@GetMapping(value = "/", produces = "application/json")
	public List<Object[]> listarDepartamentosQuantidadePessoas() {
        return pessoaRepository.contarPessoasTarefasPorDepartamento();
	}
}
