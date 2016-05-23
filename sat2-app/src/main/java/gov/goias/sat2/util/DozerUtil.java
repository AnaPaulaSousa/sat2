package gov.goias.sat2.util;

import gov.goias.sat2.representation.App;
import org.dozer.DozerBeanMapper;

/**
 * Created by ederbd on 16/05/16.
 */
public class DozerUtil {

    private static final DozerBeanMapper mapper = new DozerBeanMapper();

    static{
            mapper.addMapping(App.getMappingBuilder());
    }


    public static DozerBeanMapper getMapper() {
        return mapper;
    }
}
