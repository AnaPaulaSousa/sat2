package gov.goias.sat2.services;

import gov.goias.excecao.InfraException;
import gov.goias.historico.annotation.Historico;
import javaslang.control.Try;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by thiago-rs on 8/31/16.
 */
public abstract class CrudService<T, ID extends Serializable> {

    abstract <R extends JpaRepository<T, Serializable>> R getRepo();

    public <ID extends Serializable> Optional<T> obterPorId(final ID id) {

        return Try.of(() -> getRepo().findOne(id))
                .map(e -> Optional.of(e))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @Historico
    public <ID extends Serializable> void remover(final ID id) {
        try {
            obterPorId(id).ifPresent(aluno -> getRepo().delete(aluno));
        } catch (Exception e) {
            throw new InfraException(e);
        }
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    @Historico
    public T salvar(final T aluno) {
        return Try.of(() -> getRepo().save(aluno))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    public Long contarTodos() {
        return Try.of(() -> getRepo().count())
                .onFailure(e -> new InfraException(e))
                .get();
    }


}
