package com.newjeanssa.budongbudong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackageClasses = BudongbudongApplication.class)
@SpringBootApplication
public class BudongbudongApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudongbudongApplication.class, args);
    }

}
