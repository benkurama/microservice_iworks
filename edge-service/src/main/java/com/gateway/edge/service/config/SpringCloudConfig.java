package com.gateway.edge.service.config;

/**
 * Created by Default on 21/04/2022.
 */
import com.gateway.edge.service.security.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route("account-service", r -> r.path("/account/**")
                .filters(f -> f.filter(filter))
                .uri("lb://account-service"))

                .route("auth-service", r -> r.path("/auth/**")
                .filters(f -> f.filter(filter))
                .uri("lb://auth-service"))

                .route("workorder-service", r -> r.path("/workorder/**")
                .filters(f -> f.filter(filter))
                .uri("lb://workorder-service"))

                .route("workorder2-service", r -> r.path("/installorder/**")
                .filters(f -> f.filter(filter))
                .uri("lb://workorder-service"))

                .route("system-service", r -> r.path("/resource/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://system-service"))

                .build();
    }
/*    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder asdf) {
        return builder.routes()
                .route(r -> r.path("*//**")
                        .uri("http://localhost:1101/")
                        .id("accountModule"))

                .build();
    }*/

}