package com.newjeanssa.budongbudong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableBatchProcessing // 배치 사용을 위한 선언
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
                        .allowedOriginPatterns("*")
                        .allowedHeaders("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .exposedHeaders("*")
                        .allowedMethods("*")
                        .allowCredentials(true);
            }
        };
    }

}
