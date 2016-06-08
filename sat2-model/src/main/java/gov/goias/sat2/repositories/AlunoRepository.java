package gov.goias.sat2.repositories;

import gov.goias.sat2.entities.Aluno;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.Optional;

/**
 * Created by ederbd on 16/05/16.
 */
public interface AlunoRepository extends  PagingAndSortingRepository<Aluno, Long> , QueryByExampleExecutor<Aluno> {
    Optional<Aluno> findById(Long id);
    //Page<Aluno> queryFirst10ByNome(final String nome, final Pageable pageable);
}