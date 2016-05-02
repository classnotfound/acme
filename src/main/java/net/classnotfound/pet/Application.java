package net.classnotfound.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"net.classnotfound.pet","org.owasp.appsensor"})
public class Application {
	
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
