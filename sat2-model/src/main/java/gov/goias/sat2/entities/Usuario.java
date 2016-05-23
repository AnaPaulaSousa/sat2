package gov.goias.sat2.entities;

import java.util.Date;

/**
 * Created by thiago-rs on 3/21/16.
 */
public class Usuario {

    private String nomeUsuario;
    private PessoaFisica pessoa;
    private StatusUsuario status;
    private String senha;
    private Date ativacao;
    private Boolean isSuperUsuario;
    private String email;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public String getSenha() {
        return senha;
    }

    public Date getAtivacao() {
        return ativacao;
    }

    public Boolean getSuperUsuario() {
        return isSuperUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAtivacao(Date ativacao) {
        this.ativacao = ativacao;
    }

    public void setSuperUsuario(Boolean superUsuario) {
        isSuperUsuario = superUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nomeUsuario.equals(usuario.nomeUsuario);

    }

    @Override
    public int hashCode() {
        return nomeUsuario.hashCode();
    }
}
