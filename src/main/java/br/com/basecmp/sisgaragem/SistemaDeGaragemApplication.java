package br.com.basecmp.sisgaragem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SistemaDeGaragemApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(SistemaDeGaragemApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

        return super.configure(builder);
    }

}
