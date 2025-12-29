package com.oussama.orderservice.query.repositories;

import com.oussama.orderservice.query.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
