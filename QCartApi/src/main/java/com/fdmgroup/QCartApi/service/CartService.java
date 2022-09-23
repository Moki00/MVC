package com.fdmgroup.QCartApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.QCartApi.model.Cart;
import com.fdmgroup.QCartApi.model.Product;
import com.fdmgroup.QCartApi.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductRestApiClientService productApi;

	public void putProductInCart(int productId, Cart cart) {
		List<Product> products = cart.getProducts();
		products.add(productApi.getProductById(productId));
		updateCart(cart);
	}

	public void removeProductFromCart(int productId, Cart cart) {
		List<Product> products = cart.getProducts();
		products.remove(productApi.getProductById(productId));
		updateCart(cart);
	}

	public void emptyCart(int cartId) {
		List<Product> products = getCartById(cartId).getProducts();
		products.clear();
		updateCart(getCartById(cartId));
	}

	public void createCart(Cart cart) {
		cartRepo.save(cart);
	}

	public List<Cart> findAllCarts() {
		return cartRepo.findAll();
	}

	public Cart getCartById(int cartId) {
		return cartRepo.getById(cartId);
	}

	public Optional<Cart> findCartById(int cartId) {
		return cartRepo.findById(cartId);
	}

	public void updateCart(Cart cart) {
		cartRepo.save(cart);
	}

	public void deleteCartById(int cartId) {
		cartRepo.deleteById(cartId);
	}

}
