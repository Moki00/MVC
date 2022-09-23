package com.fdmgroup.QCartApi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.QCartApi.exception.CartNotFoundException;
import com.fdmgroup.QCartApi.model.Cart;
import com.fdmgroup.QCartApi.service.CartService;

@RestController
@RequestMapping("/api/v1/carts")
public class CartRestController {

	@Autowired
	private CartService service;

	@PostMapping
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		service.createCart(cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cart.getCartId()) //
				.toUri();
		System.out.println("Created cart at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/addProduct/{product_id}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable("product_id") int productId, @RequestBody Cart cart) {
		service.putProductInCart(productId, cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cart.getCartId()) //
				.toUri();
		System.out.println("Added product at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public List<Cart> getAllCarts() {
		return service.findAllCarts();
	}

	@GetMapping("/{cartId}")
	public Cart getCartById(@PathVariable("cartId") int cartId) throws CartNotFoundException {
		if (service.findCartById(cartId).isEmpty()) {
			throw new CartNotFoundException("Cart not found.");
		}
		return service.findCartById(cartId).get();
	}

	@PutMapping
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
		service.updateCart(cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cart.getCartId()) //
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/emptyCart/{cart_id}")
	public ResponseEntity<Cart> emptyCart(@PathVariable("cart_id") int cartId) {
		service.emptyCart(cartId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cartId) //
				.toUri();
		System.out.println("Removed product from: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/removeProduct/{product_id}")
	public ResponseEntity<Cart> removeProductFromCart(@PathVariable("product_id") int productId,
			@RequestBody Cart cart) {
		service.removeProductFromCart(productId, cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cart.getCartId()) //
				.toUri();
		System.out.println("Removed product from: " + location.toString());
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/removeProduct/{cart_id}/{product_id}")
	public ResponseEntity<Cart> removeProductFromACart(@PathVariable("product_id") int productId, @PathVariable("cart_id") int cartId,
			@RequestBody Cart cart) {
		service.removeProductFromCart(productId, cart);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(cart.getCartId()) //
				.toUri();
		System.out.println("Removed product from: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{cartId}")
	public void removeCartById(@PathVariable int cartId) {
		service.deleteCartById(cartId);
	}
}
