package gov.goias.sat2;

import gov.goias.historico.aspecto.HistoricoAspectoFuncional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import static org.mockito.Mockito.mock;

/**
 * Created by thiago-rs on 3/21/16.
 */
@Component
public class TestConfig {

    @Autowired @Getter @Setter
    private DataSource dataSource;

    @Autowired @Getter @Setter
    private HistoricoAspectoFuncional historico;

    @PostConstruct
    public void setHistorico(){

    }

}
