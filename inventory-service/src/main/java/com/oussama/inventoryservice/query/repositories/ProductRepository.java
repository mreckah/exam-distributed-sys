package com.oussama.inventoryservice.query.repositories;

import com.oussama.inventoryservice.query.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
