package gov.goias.mtb.entities;

import java.util.Date;

/**
 * Created by thiago-rs on 3/21/16.
 */
public class PessoaFisica extends Pessoa {

    private String nomeMae;
    private String nomePai;
    private Date nascimento;
    private Sexo sexo;
    private Integer cpf;
    private String rg;

    public String getNomeMae() {
        return nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Integer getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
