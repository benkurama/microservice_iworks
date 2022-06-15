package com.gateway.edge.service.config;

/**
 * Created by Default on 21/04/2022.
 */
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringCloudConfig {



/*    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder asdf) {
        return builder.routes()
                .route(r -> r.path("*//**")
                        .uri("http://localhost:1101/")
                        .id("accountModule"))

                .build();
    }*/

}