package com.oussama.inventoryservice.query.projections;

import com.oussama.inventoryservice.common.api.queries.GetAllProductsQuery;
import com.oussama.inventoryservice.common.api.queries.GetProductByIdQuery;
import com.oussama.inventoryservice.query.entities.Product;
import com.oussama.inventoryservice.query.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InventoryQueryHandler {
    private final ProductRepository productRepository;

    @QueryHandler
    public List<Product> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @QueryHandler
    public Product handle(GetProductByIdQuery query) {
        return productRepository.findById(query.getId()).orElse(null);
    }
}
