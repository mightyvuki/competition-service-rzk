package com.competitions.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator coffeeRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("home", r -> r
                        .path("/home")
                        .filters(f -> f.rewritePath("/home", "/events")
                                .addRequestHeader("x-competitions-api-key", "competition-access-key"))
                        .uri("lb://event-service"))
                .route("login", r -> r
                        .path("/login")
                        .filters(f -> f.rewritePath("/login", "/auth/login")
                                .addRequestHeader("x-competitions-api-key", "competition-access-key"))
                        .uri("lb://korisnik-service"))
                .route("register", r -> r
                        .path("/register")
                        .filters(f -> f.rewritePath("/register", "/auth/register")
                                .addRequestHeader("x-competitions-api-key", "competition-access-key"))
                        .uri("lb://korisnik-service"))
                .route("event-service", r -> r.path("/events/**")
                        .uri("lb://event-service"))
                .route("prijava-service", r -> r.path("/prijava/**")
                        .uri("lb://prijava-service"))
                .route("kotizacija-service", r -> r.path("/kotizacija/**")
                        .uri("lb://kotizacija-service"))
                .route("korisnik-service", r -> r.path("/korisnici/**")
                        .uri("lb://korisnik-service"))
                .build();
    }
}
