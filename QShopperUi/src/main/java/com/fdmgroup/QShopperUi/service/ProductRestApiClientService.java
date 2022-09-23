package com.fdmgroup.QShopperUi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.QShopperUi.exception.ProductNotFoundException;
import com.fdmgroup.QShopperUi.model.Product;

import reactor.core.publisher.Mono;

@Service
public class ProductRestApiClientService {

	@Autowired
	@Qualifier("productWebClient")
	private WebClient productWebClient;

	public void addProduct(Product product) {
		productWebClient.post() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(product), Product.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}

	public Product getProductById(int id) {
		return productWebClient.get() //
				.uri(builder -> builder.path("/{id}").build(id)) //
				.retrieve() //
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new ProductNotFoundException("product not found for ID = " + id)))
				.bodyToMono(Product.class) //
				.block();
	}

	public List<Product> getProductByName(String name) {
		return productWebClient.get() //
				.uri(builder -> builder.path("/{name}").build(name)) //
				.retrieve() //
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new ProductNotFoundException("Product not found for name: " + name)))
				.bodyToFlux(Product.class) //
				.collectList() //
				.block();
	}

	public List<Product> getAllProducts() {
		return productWebClient.get() //
				.uri(builder -> builder.path("/").build()) //
				.retrieve() //
				.bodyToFlux(Product.class) //
				.collectList() //
				.block();
	}

	public void updateProduct(Product updatedProduct) {
		productWebClient.put() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(updatedProduct), Product.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block(); //
	}

	public void deleteProductById(int id) {
		productWebClient.delete() //
				.uri(builder -> builder.path("/{id}").build(id)) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}
}
