package com.fdmgroup.QOrderApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class QOrderApiApplication {

	public static final String URI_CART_REST_API = "http://localhost:8010/api/v1/carts";

	public static void main(String[] args) {
		SpringApplication.run(QOrderApiApplication.class, args);
	}

	@Bean
	public WebClient cartWebClient() {
		return WebClient.builder() //
				.baseUrl(URI_CART_REST_API) //
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
				.build();
	}

}
