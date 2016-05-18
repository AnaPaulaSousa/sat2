package gov.goias.mtb.entities;

/**
 * Created by thiago-rs on 3/22/16.
 */
public enum Sexo {

    M("Masculino"),
    F("Feminino");

    private final String desc;

    Sexo(String desc){
        this.desc = desc;
    }
}
