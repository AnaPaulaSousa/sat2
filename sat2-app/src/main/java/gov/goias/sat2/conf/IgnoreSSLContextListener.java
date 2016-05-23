package gov.goias.sat2.conf;

import gov.goias.auth.TLSContextConfig;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Properties;

/**
 * Created by thiago-rs on 5/5/16.
 */
public class IgnoreSSLContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(IgnoreSSLContextListener.class);

    private static Properties p = new Properties(System.getProperties());

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {


    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        try {

            p.load(IgnoreSSLContextListener.class.getClassLoader()
                    .getResourceAsStream("cas-config.properties"));

            configTLSContext();

        } catch (Exception e) {
            LOGGER.error(e);
        }


    }

    /**
     * Configura o ambiente TLS caso já não tenha sido passado via script Jboss ou
     * configurado neste mesmo método para ambientes que tenham TLS habilitado via
     * propriedade control.api.cacerts
     * @throws Exception
     * @throws java.security.KeyManagementException
     * @throws java.security.NoSuchAlgorithmException
     */
    private static void configTLSContext() throws Exception {

        final boolean ignoreSSL = Boolean.parseBoolean(p.getProperty("cas-ignoressl"));

        if(System.getProperty("javax.net.ssl.trustStore") == null){

            if(ignoreSSL){

                TLSContextConfig.setUnsecureSSLContext();
                TLSContextConfig.setUnsecureHostnameVerifier();
                LOGGER.info("TLS/SSL não configurado, ignorando TLS usando TLSContextConfig.setUnsecureSSLContext");

            }else{

                LOGGER.info("TLS/SSL não configurado. System param javax.net.ssl.trustStore nulo e cas.ignoreSSL como falso");
            }

        }else if(System.getProperty("javax.net.ssl.trustStore") != null){

            LOGGER.info("TLS/SSL configurado via parâmetro da JVM javax.net.ssl.trustStore");
        }

    }

}
