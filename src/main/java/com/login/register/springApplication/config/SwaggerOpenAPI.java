package com.login.register.springApplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

public class SwaggerOpenAPI {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}
