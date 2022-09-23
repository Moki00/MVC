package com.fdmgroup.QCartApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.QCartApi.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
