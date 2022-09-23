package com.fdmgroup.QOrderApi.controller;

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

import com.fdmgroup.QOrderApi.exception.OrderNotFoundException;
import com.fdmgroup.QOrderApi.model.Order;
import com.fdmgroup.QOrderApi.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		service.createOrder(order);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{orderId}") //
				.buildAndExpand(order.getOrderId()) //
				.toUri();
		System.out.println("Created order at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@GetMapping
	public List<Order> getAllOrders() {
		return service.findAllOrders();
	}

	@GetMapping("/{orderId}")
	public Order getOrderById(@PathVariable("orderId") int orderId) throws OrderNotFoundException {
		if (service.findOrderById(orderId).isEmpty()) {
			throw new OrderNotFoundException("Order not found: " + orderId);
		}
		return service.findOrderById(orderId).get();
	}

	@PutMapping
	public ResponseEntity<Order> updateCart(@RequestBody Order circulatedBook) {
		service.updateOrder(circulatedBook);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{cartId}") //
				.buildAndExpand(circulatedBook.getOrderId()) //
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{orderId}")
	public void removeOrderById(@PathVariable int orderId) {
		service.deleteOrderById(orderId);
	}
}
