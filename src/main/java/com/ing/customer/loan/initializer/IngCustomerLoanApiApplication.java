package com.ing.customer.loan.initializer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ing.customer.loan.db.repos")
@EntityScan(basePackages = "com.ing.customer.loan.db")
@ComponentScan(basePackages = "com.ing.customer.loan")
public class IngCustomerLoanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngCustomerLoanApiApplication.class, args);
    }

}
