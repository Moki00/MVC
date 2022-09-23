package com.fdmgroup.QCartApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class QCartApiApplication {

	public static final String URI_PRODUCT_REST_API = "http://localhost:8030/api/v1/products";

	public static void main(String[] args) {
		SpringApplication.run(QCartApiApplication.class, args);
	}

	@Bean
	public WebClient productWebClient() {
		return WebClient.builder() //
				.baseUrl(URI_PRODUCT_REST_API) //
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
				.build();
	}

}
