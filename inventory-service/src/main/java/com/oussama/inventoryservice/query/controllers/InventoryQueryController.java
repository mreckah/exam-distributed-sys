package com.oussama.inventoryservice.query.controllers;

import com.oussama.inventoryservice.common.api.queries.GetAllProductsQuery;
import com.oussama.inventoryservice.common.api.queries.GetProductByIdQuery;
import com.oussama.inventoryservice.query.entities.Product;
import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/queries/inventory")
@AllArgsConstructor
public class InventoryQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/products")
    public CompletableFuture<List<Product>> getAllProducts() {
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class));
    }

    @GetMapping("/products/{id}")
    public CompletableFuture<Product> getProduct(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(Product.class));
    }
}
