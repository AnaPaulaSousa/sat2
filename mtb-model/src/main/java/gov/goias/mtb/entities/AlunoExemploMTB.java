package gov.goias.mtb.entities;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ederbd on 16/05/16.
 */

@Entity
@Table(name = "ALUNO_EXEMPLO")
@Data
public class AlunoExemploMTB {

    @Id
    @Column(name = "ID_ALUNO_EXEMPLO")
    @GeneratedValue(generator = "ALUNO_EXEMPLO_SQ")
    @GenericGenerator(name = "ALUNO_EXEMPLO_SQ", strategy = "gov.goias.persistencia.GoiasGenerator", parameters = { @org.hibernate.annotations.Parameter(name = "sequence", value = "ALUNO_EXEMPLO_SQ") })
    private Integer id;

    @Column(name = "NOME_ALUNO_EXEMPLO")
    private String nome;


}
