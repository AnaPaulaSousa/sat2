package gov.goias.sat2.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    @GeneratedValue
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

}