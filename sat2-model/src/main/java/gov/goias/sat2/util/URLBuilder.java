package gov.goias.sat2.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thiago-rs on 8/11/15.
 */
public class URLBuilder {
//
//    private String protocol = "http";
//    private String host = "localhost";
//    private String port = "8080";
//    private String context = "sat2";
//    private String servlet = "app";
//
//    public static URLBuilder getInstance(){
//        return new URLBuilder();
//    }
//
//    public URLBuilder protocol(String protocol){
//        this.protocol = protocol;
//        return this;
//    }
//
//    public URLBuilder host(String host){
//        this.host = host;
//        return this;
//    }
//
//    public URLBuilder port(String port){
//        this.port = port;
//        return this;
//    }
//
//    public URLBuilder context(String context){
//        this.context = context;
//        return this;
//    }
//
//    public URLBuilder servlet(String servlet){
//        this.servlet = servlet;
//        return this;
//    }
//
//    public URL asURL() throws MalformedURLException {
//        return new URL(build(null));
//    }
//
//
//    public URL asURL(String resource) throws MalformedURLException {
//        return new URL(build(resource));
//    }
//
//
//    public String build() {
//        return build(null);
//    }
//
//    public String build(String resource) {
//
//        try {
//            if (System.getProperty("jetty.port") != null)
//                return mkString(System.getProperty("jetty.port"), resource);
//        } catch (Exception e) {
//            return mkString(port, resource);
//        }
//
//        return mkString(port, resource);
//    }
//
//    private String mkString(String port, String resouce){
//
//        servlet = servlet == null || servlet.trim().isEmpty()?"" : "/"+servlet;
//
//        return String.format("%s://%s:%s/%s%s%s",protocol,host,port,context,servlet, resouce == null?"":"/"+resouce);
//    }

}
