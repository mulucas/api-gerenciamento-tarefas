package desafio.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import desafio.api.rest.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Long> {

	@Query("SELECT d.nome, COUNT(DISTINCT p), COUNT(t) "
			+ "FROM Departamento d LEFT JOIN d.pessoas p LEFT JOIN p.tarefas t " + "GROUP BY d.nome")
	List<Object[]> contarPessoasTarefasPorDepartamento();

	@Query("SELECT p, AVG(t.duracao) FROM Pessoa p LEFT JOIN p.tarefas t WHERE p.nome = :nome GROUP BY p")
	List<Object[]> buscarPessoasComMediaDuracaoPorNome(@Param("nome") String nome);

	List<Pessoa> findByNome(String nome);
}
