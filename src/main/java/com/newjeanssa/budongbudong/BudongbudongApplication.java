package com.newjeanssa.budongbudong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@MapperScan(basePackageClasses = BudongbudongApplication.class)
@SpringBootApplication
public class BudongbudongApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudongbudongApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:9000")
                        .allowedOriginPatterns("*")
                        .allowedHeaders("*")
                        .exposedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }

}
