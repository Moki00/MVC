package com.fdmgroup.QShopperUi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.QShopperUi.exception.ProductNotFoundException;
import com.fdmgroup.QShopperUi.model.Cart;
import com.fdmgroup.QShopperUi.model.Product;
import com.fdmgroup.QShopperUi.service.ShoppingService;

@Controller
public class UiController {

//	@Autowired
//	private Product product;

	// controller calls on model Services
	@Autowired
	private ShoppingService shoppingService;

	// controller shouldn't call on Rest Api?
//	@Autowired
//	private ProductRestApiClientService productRestApiClientService;

	/*
	 * The logger is declared here for this class.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UiController.class);

	/*
	 * This request changes the view to return to the home page.
	 */
	@RequestMapping({ "/", "/home" })
	public String goToHome() {
		try {
			LOGGER.trace("User returned to home page");
			return "index";
		} catch (Exception e) {
			LOGGER.error("cannot get to index.html", e);
		}
		return "index";
	}

	/*
	 * This request changes the view to show all the products in the database.
	 */
	@RequestMapping("/showProducts")
	public String goToShowProducts(Model model) {
		try {
			List<Product> products = shoppingService.findAllProducts();
			model.addAttribute("products", products);
		} catch (Exception e) {
			LOGGER.error("error at goToShowProducts in UiController", e);
		}
		return "showproducts";
	}
	
	@RequestMapping("/cart")
	public String goToCart(Model model) {
		try {
			List<Product> products = shoppingService.findAllProducts();
			model.addAttribute("products", products);
		} catch (Exception e) {
			LOGGER.error("error at goToCart in UiController", e);
		}
		return "show-cart"; // html
	}

	/*
	 * This request changes the view to show only the products searched for. The
	 * database uses the search term in the product name. A partial word is okay,
	 * but blank is not acceptable.
	 */
	@RequestMapping("/searchByProductName")
	public String findProduct(@RequestParam String productName, Model model) {
		try {
			List<Product> products = shoppingService.getProductByName(productName);
			model.addAttribute("products", products);
		} catch (Exception e) {
			LOGGER.error("error at searchByProduct in UiController", e);
		}
		return "searchList";
	}

	@GetMapping("/add/{id}")
	public String goAddProductToCart(@PathVariable("id") int id, Model model) {
		// pull product
		// add to model
		List<Cart> carts = shoppingService.findAllCarts();
		model.addAttribute("carts", carts);
		
		Product product = shoppingService.findProductById(id);
		model.addAttribute("product", product);

//		shoppingService.putProductInCart(id);
		return "myCart";
	}

//	@GetMapping("/addToCart/{id}")
//	public String goAddProductToCart(@PathVariable("id") int id, Cart cart) {
//		shoppingService.putProductInCart(id);
//		return "redirect:/showProducts/";
//	}

	@GetMapping("/remove/{id}")
	public String goRemoveProductFromCart(@PathVariable("id") int id) {
//		shopping.removeProductFromCart(id);
		return "redirect:/show/" + id;
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public String handleProductNotFoundException(Model model, Exception e) {
		model.addAttribute("error", e);
		return "show-error";
	}

	@GetMapping("/show/{id}")
	public String toProduct(Model model, @PathVariable("id") int id) {
		Product product = shoppingService.findProductById(id);
		if (product != null) {
//			model.addAttribute("circulatedBook", circulatedBook);
//			model.addAttribute("isAvailable", shopping.isAvailable(circulatedBook));
//			model.addAttribute("isOnLoan", shopping.isOnLoan(circulatedBook));
//			Product product = shopping.findProductById(id);
//			model.addAttribute("product", book);
		}
		return "detailed-circulated-book";
	}

}
