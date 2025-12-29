package com.oussama.inventoryservice.query.projections;

import com.oussama.inventoryservice.common.api.events.CategoryCreatedEvent;
import com.oussama.inventoryservice.common.api.events.ProductCreatedEvent;
import com.oussama.inventoryservice.common.api.events.ProductQuantityDeductedEvent;
import com.oussama.inventoryservice.common.api.events.ProductStatusUpdatedEvent;
import com.oussama.inventoryservice.query.entities.Category;
import com.oussama.inventoryservice.query.entities.Product;
import com.oussama.inventoryservice.query.repositories.CategoryRepository;
import com.oussama.inventoryservice.query.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryEventHandler {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        Category category = new Category();
        category.setId(event.getId());
        category.setName(event.getName());
        category.setDescription(event.getDescription());
        categoryRepository.save(category);
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        product.setStatus(event.getStatus());
        categoryRepository.findById(event.getCategoryId()).ifPresent(product::setCategory);
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        productRepository.findById(event.getId()).ifPresent(product -> {
            product.setStatus(event.getStatus());
            productRepository.save(product);
        });
    }

    @EventHandler
    public void on(ProductQuantityDeductedEvent event) {
        productRepository.findById(event.getId()).ifPresent(product -> {
            product.setQuantity(product.getQuantity() - event.getQuantity());
            productRepository.save(product);
        });
    }
}
