package gov.goias.sat2.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ederbd on 16/05/16.
 */

@Entity
@Table(name = "TB_ALUNO")
@Data
public class Aluno {
    @Id
    @Column(name = "ALUN_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ALUN_NOME")
    private String nome;

    @Column(name = "ALUN_EMAIL")
    private String email;

}