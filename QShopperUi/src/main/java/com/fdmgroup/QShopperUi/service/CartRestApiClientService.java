package com.fdmgroup.QShopperUi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.QShopperUi.exception.CartNotFoundException;
import com.fdmgroup.QShopperUi.model.Cart;

import reactor.core.publisher.Mono;

@Service
public class CartRestApiClientService {

	@Autowired
	@Qualifier("cartWebClient")
	private WebClient cartWebClient;

	public void addCart(Cart cart) {
		cartWebClient.post() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(cart), Cart.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}

	public Cart getCartById(int cartId) {
		return cartWebClient.get() //
				.uri(builder -> builder.path("/{cartId}").build(cartId)) //
				.retrieve() //
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new CartNotFoundException("cart not found for ID = " + cartId)))
				.bodyToMono(Cart.class) //
				.block();
	}

	public List<Cart> getAllCarts() {
		return cartWebClient.get() //
				.uri(builder -> builder.path("/").build()) //
				.retrieve() //
				.bodyToFlux(Cart.class) //
				.collectList() //
				.block();
	}

	public void updateCart(Cart updatedCart) {
		cartWebClient.put() //
				.uri(builder -> builder.path("/").build()) //
				.body(Mono.just(updatedCart), Cart.class) //
				.retrieve() //
				.toBodilessEntity() //
				.block(); //
	}

	public void deleteCartById(int id) {
		cartWebClient.delete() //
				.uri(builder -> builder.path("/{id}").build(id)) //
				.retrieve() //
				.toBodilessEntity() //
				.block();
	}

}
