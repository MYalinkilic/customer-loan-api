package com.ing.customer.loan.initializer;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        String yaml = new String(
                Files.readAllBytes(Paths.get("src/main/resources/static/customerLoanAPI.yaml")), StandardCharsets.UTF_8
        );
        return new Yaml().loadAs(yaml, OpenAPI.class);
    }
}
