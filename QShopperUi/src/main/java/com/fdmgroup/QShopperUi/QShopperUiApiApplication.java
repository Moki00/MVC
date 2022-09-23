package com.fdmgroup.QShopperUi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class QShopperUiApiApplication {

	public static final String URI_CART_REST_API = "http://localhost:8010/api/v1/carts";
	public static final String URI_ORDER_REST_API = "http://localhost:8020/api/v1/orders";
	public static final String URI_PRODUCT_REST_API = "http://localhost:8030/api/v1/products";

	public static void main(String[] args) {
		SpringApplication.run(QShopperUiApiApplication.class, args);
	}

	@Bean
	public WebClient productWebClient() {
		return WebClient.builder() //
				.baseUrl(URI_PRODUCT_REST_API) //
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
				.build();
	}

	@Bean
	public WebClient orderWebClient() {
		return WebClient.builder() //
				.baseUrl(URI_ORDER_REST_API) //
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
				.build();
	}
	
	@Bean
	public WebClient cartWebClient() {
		return WebClient.builder() //
				.baseUrl(URI_CART_REST_API) //
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) //
				.build();
	}
}
