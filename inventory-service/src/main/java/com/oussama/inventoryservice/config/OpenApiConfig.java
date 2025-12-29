package com.oussama.inventoryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI inventoryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .description(
                                "Microservice for managing inventory and categories using Event Sourcing and CQRS.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Oussama")
                                .email("contact@oussama.com")));
    }
}
