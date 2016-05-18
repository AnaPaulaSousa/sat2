package gov.goias.mtb.entities;

/**
 * Created by thiago-rs on 3/23/16.
 */
public class Perfil {

    private Integer codigo;
    private Sistema sistema;
    private String descricao;
    private String tipoPerfil = "A";
    private boolean servico = false;

    public Integer getCodigo() {
        return codigo;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public boolean isServico() {
        return servico;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    public void setServico(boolean servico) {
        this.servico = servico;
    }
}
