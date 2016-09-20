package gov.goias.sat2.conf;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * <b>Título:</b> WebApp
 * <br><b>Descrição:</b> Configurações da aplicação<br>
 * Define como obter usuário para geração de histórico<br>
 * Coleção de métodos utilitários a aplicação
 * <br><b>Copyright:</b> Copyright(c) 2015
 * <br><b>Empresa:</b> SEGPLAN
 */
@Component
public class WebApp extends ResourceConfig {
    private static final Logger LOGGER = Logger.getLogger(WebApp.class);

    public WebApp() {
        packages("gov.goias").register(RolesAllowedDynamicFeature.class);
    }

    /**
     * Obtem ordem de parametros para paginação usando www.datatables.net/
     * @param request HttpRequest
     * @param columns lista de colunas da tabela
     * @return mapa ordenado
     */
    public static Map<String,String> getPaginationOrder(final HttpServletRequest request, final String... columns){
        final Map<String,String> order = new LinkedHashMap<>();
        IntStream.range(0, columns.length).forEach(i -> {
            try{
                final String colIndex = request.getParameterValues(String.format("order[%s][column]",i))[0];
                final String pValue = request.getParameterValues(String.format("order[%s][dir]", i))[0];
                if(pValue != null && !"".equals(pValue))
                    order.put(columns[Integer.parseInt(colIndex)],pValue);
            }catch (Exception e){
                LOGGER.debug(e);
            }
        });
        return order;
    }

}