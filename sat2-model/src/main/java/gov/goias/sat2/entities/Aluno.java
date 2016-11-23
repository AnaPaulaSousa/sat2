package gov.goias.sat2.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ederbd on 16/05/16.
 */

@Entity
@Table(name = "TB_ALUNO")
@Data
public class Aluno {
    @Id
    @Column(name = "ALUN_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "ALUN_NOME")
    private String nome;

    @Column(name = "ALUN_EMAIL")
    private String email;

    @Column(name = "ALUN_NASCIMENTO")
    private Date nascimento;

    @Column(name = "ALUN_SEXO")
    private String sexo;

    @Column(name = "ALUN_SITUACAO")
    private Boolean situacao;

    public static Specification<Aluno> id(final Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.<Long>get("id"), id);
    }

    public static Specification<Aluno> nomeIniciando(final String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), nome.toUpperCase()+"%");
    }

    public static Specification<Aluno> comEmail(final String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<Aluno> nascidoEm(final Date nascimento) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.<Date>get("nascimento"), nascimento);
    }
}