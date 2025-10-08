package com.rcf.gatewayservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de RCF Solutions Information Technology")
                        .version("1.0.0")
                        .description("API para gestionar de productos y servicios.")
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Gateway")
                ));
    }
}
