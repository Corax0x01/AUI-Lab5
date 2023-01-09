package szymanski.jakub.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}


	@Bean

	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder
				.routes()
				.route("publishing_houses", r -> r
						.host("172.28.0.4:8080")
						.and()
						.path("/api/publishing_houses/{name}", "/api/publishing_houses")
						.uri("http://172.28.0.4:8081"))
				.route("books", r -> r
						.host("172.28.0.4:8080")
						.and()
						.path("/api/books", "/api/books/**", "/api/publishing_houses/{name}/books",
								"/api/publishing_houses/{name}/books/**")
						.uri("http://172.28.0.4:8082"))
				.build();
	}


	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}

}
