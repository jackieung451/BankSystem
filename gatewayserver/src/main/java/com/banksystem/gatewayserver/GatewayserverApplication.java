package com.banksystem.gatewayserver;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/banksystem/accounts/**")
	            .filters(f -> f.rewritePath("/banksystem/accounts/(?<segment>.*)","/${segment}")
	            				.addResponseHeader("X-Response-Time",new Date().toString()))
	            .uri("lb://ACCOUNTS")).
	        route(p -> p
		            .path("/banksystem/loans/**")
		            .filters(f -> f.rewritePath("/banksystem/loans/(?<segment>.*)","/${segment}")
		            		.addResponseHeader("X-Response-Time",new Date().toString()))
		            .uri("lb://LOANS")).
	        route(p -> p
		            .path("/banksystem/cards/**")
		            .filters(f -> f.rewritePath("/banksystem/cards/(?<segment>.*)","/${segment}")
		            		.addResponseHeader("X-Response-Time",new Date().toString()))
		            .uri("lb://CARDS")).build();
	}
	
}
