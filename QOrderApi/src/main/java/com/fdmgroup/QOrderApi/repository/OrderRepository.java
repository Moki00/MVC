package com.fdmgroup.QOrderApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.QOrderApi.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
