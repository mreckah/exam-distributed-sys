package com.oussama.inventoryservice.query.repositories;

import com.oussama.inventoryservice.query.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
