package edu.unimagdalena.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user", r -> r.path("/api/v1/users/**")
                        .uri("http://localhost:8080")) // URL del microservicio User
                .route("order", r -> r.path("/api/v1/orders/**")
                        .uri("http://localhost:8082")) // URL del microservicio Order
                .route("product", r -> r.path("/api/v1/products/**")
                        .uri("http://localhost:8081")) // URL del microservicio Product
                .build();
    }
}

