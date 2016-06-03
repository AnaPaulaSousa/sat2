package gov.goias.sat2.representation;

import gov.goias.sat2.entities.ProtocoloAuth;
import gov.goias.sat2.entities.Sistema;
import gov.goias.sat2.entities.StatusSistema;
import gov.goias.sat2.util.DozerUtil;
import lombok.Getter;
import lombok.Setter;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import javax.xml.bind.annotation.XmlRootElement;

import static org.dozer.loader.api.TypeMappingOptions.mapId;
import static org.dozer.loader.api.TypeMappingOptions.mapNull;

/**
 * Created by thiago-rs on 4/11/16.
 */
@XmlRootElement
public class App {

    @Getter @Setter private Integer id;
    @Getter @Setter private String sigla;
    @Getter @Setter private String descricao;
    @Getter @Setter private String conexao;
    @Getter @Setter private String mensagem;
    @Getter @Setter private String logotipo;
    @Getter @Setter private StatusSistema status;
    @Getter @Setter private String nomeAplicacao;
    @Getter @Setter private ProtocoloAuth protocolo;

    public static BeanMappingBuilder getMappingBuilder(){
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(Sistema.class,
                        App.class,
                        TypeMappingOptions.oneWay(),
                        mapId("A"),
                        mapNull(true)
                );
            }
        };
    }

    public static App from(final Sistema s){
        return DozerUtil.getMapper().map(s,App.class);
    }

    public Sistema toSistema(){
        return DozerUtil.getMapper().map(this,Sistema.class);
    }

}