package desafio.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Departamento;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento, Long> {

}
