package com.oussama.orderservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .description("Microservice for managing orders and sagas using Event Sourcing and CQRS.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Oussama")
                                .email("contact@oussama.com")));
    }
}
