package com.revature.server;

import com.revature.server.servlets.FileServlet;
import com.revature.server.servlets.HelloServlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class App {
    public static void main(String[] args) throws LifecycleException {
        Tomcat server = new Tomcat();
        server.setBaseDir(System.getProperty("java.io.tmpdir"));
        server.setPort(8080);
        server.getConnector();
        server.addContext("", null);

        server.addServlet("", "file", new FileServlet())
            .addMapping("/*");
        server.addServlet("", "hello", new HelloServlet())
            .addMapping("/hello");
        
        server.start();
        server.getServer().await();
    }
}
