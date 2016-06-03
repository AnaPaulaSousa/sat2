package gov.goias.sat2.util;

import gov.goias.sat2.representation.App;
import gov.goias.sat2.view.model.Aluno;
import org.dozer.DozerBeanMapper;

/**
 * Created by ederbd on 16/05/16.
 */
public class DozerUtil {
    private static final DozerBeanMapper mapper = new DozerBeanMapper();

    static{
            mapper.addMapping(Aluno.getMappingBuilder());
    }

    public static DozerBeanMapper getMapper() {
        return mapper;
    }

}