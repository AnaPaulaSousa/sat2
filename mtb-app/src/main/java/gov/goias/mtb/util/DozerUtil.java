package gov.goias.mtb.util;

import gov.goias.mtb.representation.App;
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
