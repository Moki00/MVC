package com.fdmgroup.QCartApi.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name = "CART_API")
public class Cart {

	/*
	 * 3 attributes are id, total price and a list of products
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CART_ID")
	private int cartId;

	@Column(name = "TOTAL_PRICE")
	private double totalPrice;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CART_PRODUCT", joinColumns = //
	@JoinColumn(name = "CART_ID", nullable = false), inverseJoinColumns = //
	@JoinColumn(name = "PRODUCT_ID"))
	private List<Product> products;

	/*
	 * getters and setters for the 3 attributes
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", totalPrice=" + totalPrice + ", products=" + products + "]";
	}

}
