package gov.goias.mtb.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * Created by thiago-rs on 3/18/16.
 */
public abstract class PasRepository {

    protected final Logger LOGGER;

    protected NamedParameterJdbcTemplate npJdbcTemplate;

    protected SimpleJdbcInsert insert;

    public PasRepository() {
        this.LOGGER = Logger.getLogger(getClass());
    }

    @Autowired
    public void seJdbcTemplate(JdbcTemplate jdbcTemplate) {

        this.insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(getTableName())
                .usingColumns(getInsertColumnNames());

        this.npJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

    }

    public NamedParameterJdbcTemplate getTemplate(){
        return this.npJdbcTemplate;
    }

    public abstract String getTableName();

    public abstract String[] getInsertColumnNames();

}
