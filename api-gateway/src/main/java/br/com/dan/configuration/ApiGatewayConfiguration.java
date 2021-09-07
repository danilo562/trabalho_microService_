package br.com.dan.configuration;

import java.util.function.Function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
				.filters(f -> f.addRequestHeader("hello", "Word")
						          .addRequestParameter("Hello1", "Word1")    )
				.uri("http://httpbin.org:80") )
				.route(p -> p.path("/conta-corrente/**")   //para nao ter que ficar digitanto todo caminho.
						.uri("lb://conta-corrente"))
				.route(p -> p.path("/investimento/**")
						.uri("lb://investimento"))
				.build();
		
	}
	
	

}
