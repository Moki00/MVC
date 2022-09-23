package com.fdmgroup.QShopperUi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.QShopperUi.exception.OrderNotFoundException;
import com.fdmgroup.QShopperUi.model.Order;

import reactor.core.publisher.Mono;

@Service
public class OrderRestApiClientService {

	@Autowired
	@Qualifier("orderWebClient")
	private WebClient orderWebClient;

	// C in CRUD
	public void addOrder(Order order) {
		orderWebClient.post() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(order), Order.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}

	// R in CRUD
	public Order getOrderById(int orderId) {
		return orderWebClient.get() //
				.uri(builder -> builder.path("/{orderId}").build(orderId)) //
				.retrieve() //
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new OrderNotFoundException("Order not found for ID = " + orderId)))
				.bodyToMono(Order.class) //
				.block();
	}

	// R in CRUD
	public List<Order> getAllCarts() {
		return orderWebClient.get() //
				.uri(builder -> builder.path("/").build()) //
				.retrieve() //
				.bodyToFlux(Order.class) //
				.collectList() //
				.block();
	}

	// U in CRUD
	public void updateOrder(Order order) {
		orderWebClient.put() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(order), Order.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block(); //
	}

	// D in CRUD
	public void deleteOrderById(int id) {
		orderWebClient.delete() //
				.uri(builder -> builder.path("/{id}").build(id)) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}

}
