package com.kpi.ip41m.pceshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Andrii on 16.12.2015.
 */
@SpringBootApplication
public class PcEshopApplicationWar extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PcEshopApplicationWar.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PcEshopApplicationWar.class, args);
    }
}
