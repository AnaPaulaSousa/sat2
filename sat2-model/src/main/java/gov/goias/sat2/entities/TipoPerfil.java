package gov.goias.sat2.entities;

/**
 * Created by thiago-rs on 3/23/16.
 */
public enum TipoPerfil {

    A("Ativo"),
    I("Inativo"),
    D("Desabilitado");

    private final String desc;

    TipoPerfil(String desc){
        this.desc = desc;
    }

}
