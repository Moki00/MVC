package com.fdmgroup.QShopperUi.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Order {

	/*
	 * 3 attributes
	 */
	private int orderId;
	private String customerName;
	private Cart cart;

	public int getOrderId() {
		return orderId;
	}

	/*
	 * getters and setters
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerName=" + customerName + ", cart=" + cart + "]";
	}

}
