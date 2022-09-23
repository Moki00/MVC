package com.fdmgroup.QOrderApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.QOrderApi.model.Order;
import com.fdmgroup.QOrderApi.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository cartRepo;

	@Autowired
	private CartRestApiClientService cartApi;

	
	// fix
	public void makeCartAnOrder(int cartId, Order order) {
		cartApi.getCartById(cartId);
		updateOrder(order);
	}

	public void createOrder(Order order) {
		cartRepo.save(order);
	}

	public List<Order> findAllOrders() {
		return cartRepo.findAll();
	}

	public Order getOrderById(int orderId) {
		return cartRepo.getById(orderId);
	}

	public Optional<Order> findOrderById(int orderId) {
		return cartRepo.findById(orderId);
	}

	public void updateOrder(Order order) {
		cartRepo.save(order);
	}

	public void deleteOrderById(int orderId) {
		cartRepo.deleteById(orderId);
	}

}
