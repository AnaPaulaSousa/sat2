package gov.goias.sat2.entities;


import gov.goias.sat2.Convertible;

import static gov.goias.sat2.entities.ProtocoloAuth.CONTROLLER;

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

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setSigla(final String sigla) {
        this.sigla = sigla;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public void setConexao(final String url) {
        this.conexao = url;
    }

    public void setMensagem(final String mensagem) {
        this.mensagem = mensagem;
    }

    public void setLogotipo(final String url) {
        this.logotipo = url;
    }

    public void setStatus(final StatusSistema valor) {
        this.status = valor;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setNomeAplicacao(final String nome) {
        this.nomeAplicacao = nome;
    }

    public void setInternet(final boolean internet) {
        this.internet = internet;
    }

    public void setIntranet(final boolean intranet) {
        this.intranet = intranet;
    }

    public void setProtocolo(final ProtocoloAuth protocolo) {
        this.protocolo = protocolo;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Sistema sistema = (Sistema) o;
        return sigla.equals(sistema.sigla);
    }

    @Override
    public int hashCode() {
        return sigla.hashCode();
    }

}