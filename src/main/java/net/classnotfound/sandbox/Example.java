package net.classnotfound.sandbox;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
public class Example {

	@RequestMapping("/")
    String home() {
        return "Server is working!";
    }

//    public static void main(String[] args) throws Exception {
//        SpringApplication.run(Example.class, args);
//    }

}
