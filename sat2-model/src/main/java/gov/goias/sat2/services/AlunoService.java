package gov.goias.sat2.services;

import gov.goias.excecao.InfraException;
import gov.goias.historico.annotation.Historico;
import gov.goias.sat2.entities.Aluno;
import gov.goias.sat2.repositories.AlunoRepository;
import javaslang.control.Option;
import javaslang.control.Try;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

/**
 * Created by thiago-rs on 4/11/16.
 */
@Service
public class AlunoService {
    private final Logger log = Logger.getLogger(getClass());

    @Autowired
    private AlunoRepository repository;

    public Optional<Aluno> obterPorId(final Long id){
        return Try.of(() -> repository.findById(id))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    @Transactional
    @Historico
    public Aluno salvar(final Aluno aluno){
        return Try.of(() -> repository.save(aluno))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    @Transactional(noRollbackFor = EmptyResultDataAccessException.class)
    @Historico
    public void remover(final Long id){
        try{
            repository.delete(id);
        }catch(EmptyResultDataAccessException e){
            log.debug(e);
        }catch(Exception e){
            new InfraException(e);
        }
    }

    public Page<Aluno> listarTodos(final PageRequest page){
        return Try.of(() -> repository.findAll(page))
                .onFailure(e -> new InfraException(e))
                .get();
    }

    public Page<Aluno> listarPaginado(final PageRequest page){
        return Try.of(() -> repository.findAll(page))
            .onFailure(e -> new InfraException(e))
            .get();
    }

    public Long contarTodos(){
        return Try.of(() -> repository.count())
                .onFailure(e -> new InfraException(e))
                .get();
    }

//    public Long contarFitrados(final Aluno filtro) throws InfraException {
//        final Example<Aluno> ex = Example.of(filtro);
////        ExampleMatcher matcher = ExampleMatcher.matching().
////        .withMatcher("firstname", endsWith())
////                .withMatcher("lastname", startsWith().ignoreCase());
////
////        Example<Person> example = Example.of(new Person("Jon", "Snow"), matcher);
////        repo.count(example);
//        return Try.of(() -> repository.count(ex))
//                .onFailure(e -> new InfraException(e))
//                .get();
//
//    }




}