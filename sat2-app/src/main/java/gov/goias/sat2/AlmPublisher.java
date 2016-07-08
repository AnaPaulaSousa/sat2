package gov.goias.sat2;

import javaslang.control.Try;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class AlmPublisher {

    private final static String ERRO_EXTRAIR_INFO = "Não é possível extrair informações da construção";

    public static Map<String,String> publish(HttpServletRequest req){
        checkNotNull(req, ERRO_EXTRAIR_INFO);
        return publish(req.getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));
    }

    public static Map<String,String> publish(InputStream is){

        checkNotNull(is, ERRO_EXTRAIR_INFO);

        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader bf = new BufferedReader(isr);

        return Try.of(() -> toMap(bf))
                .onFailure(e -> Collections.emptyMap())
                .get();
    }

    private static String buildId(final Map<String,String> manifest){
        final String version = manifest.get("Build-Version").trim();
        final String number = manifest.get("Build-Number").trim();
        return String.format("v%sb%s",version,number);
    }


    public static String toJson(final Map<String,String> manifest) {
        return "{"+
                "\"buildId\":"+"\""+buildId(manifest)+"\""+
                ",\"buildTime\":"+"\""+manifest.get("Build-Timestamp")+"\""+
                ",\"commitRev\":"+"\""+manifest.get("Commit-Revision")+"\""+
                ",\"repoUrl\":"+"\""+manifest.get("Repo-Url")+"\""+
                "}";
    }

    public static AlmRep toRep(final Map<String,String> manifest) {

        final AlmRep almRep = new AlmRep();
        almRep.setBuildId(buildId(manifest));
        almRep.setBuildTime(manifest.get("Build-Timestamp"));
        almRep.setCommitRev(manifest.get("Commit-Revision"));
        almRep.setRepoUrl(manifest.get("Repo-Url"));

        return almRep;
    }


    public static Map<String,String> toMap(final BufferedReader bf) throws IOException {

        final Map<String,String> manifest = new HashMap<>();

        String line;

        while((line = bf.readLine())!=null){

            if(line != null && !line.isEmpty()){
                final String k = line.substring(0,line.indexOf(":"));
                final String v = line.substring(line.indexOf(":")+2);
                manifest.put(k,v);
            }

        }

        return manifest;

    }

    @XmlRootElement
    @Data
    public static class AlmRep {

        private String commitRev;
        private String repoUrl;
        private String buildId;
        private String buildTime;

    }

}
