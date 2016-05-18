package gov.goias.mtb.entities;


import gov.goias.mtb.Convertible;

import static gov.goias.mtb.entities.ProtocoloAuth.CONTROLLER;

/**
 * Created by thiago-rs on 2/23/15.
 */

public class Sistema implements Convertible {

    private Integer id;
    private String sigla;
    private String descricao;
    private String conexao;
    private String mensagem;
    private String logotipo;
    private StatusSistema status;
    private String email;
    private String nomeAplicacao;
    private boolean internet = true;
    private boolean intranet = true;
    private ProtocoloAuth protocolo = CONTROLLER;

    public Integer getId() {
        return id;
    }

    public String getSigla() {
        return sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getConexao() {
        return conexao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public StatusSistema getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getNomeAplicacao() {
        return nomeAplicacao;
    }

    public boolean isInternet() {
        return internet;
    }

    public boolean isIntranet() {
        return intranet;
    }

    public ProtocoloAuth getProtocolo() {
        return protocolo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public void setStatus(StatusSistema status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public void setIntranet(boolean intranet) {
        this.intranet = intranet;
    }

    public void setProtocolo(ProtocoloAuth protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sistema sistema = (Sistema) o;

        return sigla.equals(sistema.sigla);

    }

    @Override
    public int hashCode() {
        return sigla.hashCode();
    }

}