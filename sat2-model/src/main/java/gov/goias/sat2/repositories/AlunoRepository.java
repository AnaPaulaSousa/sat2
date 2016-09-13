package gov.goias.sat2.repositories;

import gov.goias.sat2.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Created by ederbd on 16/05/16.
 */
public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor {
//    Optional<Aluno> findById(Long id);
    //Page<Aluno> queryFirst10ByNome(final String nome, final Pageable pageable);
}