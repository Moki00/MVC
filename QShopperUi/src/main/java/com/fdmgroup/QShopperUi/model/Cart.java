package com.fdmgroup.QShopperUi.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Cart {

	private int productId;
	private int cartId;
	private int countInCart;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCountInCart() {
		return countInCart;
	}

	public void setCountInCart(int countInCart) {
		this.countInCart = countInCart;
	}

	@Override
	public String toString() {
		return "Cart [productId=" + productId + ", cartId=" + cartId + ", countInCart=" + countInCart + "]";
	}

}
