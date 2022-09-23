package com.fdmgroup.QShopperUi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fdmgroup.QShopperUi.model.Cart;
import com.fdmgroup.QShopperUi.model.Product;

@Service
public class ShoppingService {

	@Autowired
	private ProductRestApiClientService productRestApiClientService;

	@Autowired
	private CartRestApiClientService cartRestApiClientService;

	@Autowired
	private OrderRestApiClientService orderClientService;

	public Cart findCartById(int id) {
		return cartRestApiClientService.getCartById(id);
	}

	public List<Product> findAllProducts() {
		return productRestApiClientService.getAllProducts();
	}

	public Product findProductById(int id) {
		return productRestApiClientService.getProductById(id);
	}

	public List<Product> getProductByName(String name) {
		return productRestApiClientService.getProductByName(name);
	}

	public List<Cart> findAllCarts() {
		return cartRestApiClientService.getAllCarts();
	}

	public void putProductInCart(int productId, Model model) {
		// theres no cart
//		if(cartRestApiClientService.getAllCarts()) {
//			cartRestApiClientService.addCart(null);
//		}
//		
//		Cart cart = findCartById(productId)
//		List<Product> products = cart.getProducts();
//		products.add(productApi.getProductById(productId));
//		updateCart(cart);
	}
//
//	public void removeProductFromCart(int productId, Cart cart) {
//		List<Product> products = cart.getProducts();
//		products.remove(productApi.getProductById(productId));
//		updateCart(cart);
//	}

}
