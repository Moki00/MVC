package com.fdmgroup.QOrderApi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "CART_API")
public class Cart {

	/*
	 * 2 attributes are id and total price
	 */
	@Id
	@Column(name = "CART_ID")
	private int cartId;

	@Column(name = "TOTAL_PRICE")
	private double totalPrice;

	/*
	 * getters and setters for the 2 attributes
	 */
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + "]";
	}

}
