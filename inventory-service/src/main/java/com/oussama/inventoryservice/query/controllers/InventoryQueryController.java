package com.oussama.inventoryservice.query.controllers;

import com.oussama.inventoryservice.common.api.queries.GetAllProductsQuery;
import com.oussama.inventoryservice.common.api.queries.GetProductByIdQuery;
import com.oussama.inventoryservice.query.entities.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Inventory Queries", description = "Endpoints for querying inventory data")
public class InventoryQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/products")
    @Operation(summary = "Get all products")
    public CompletableFuture<List<Product>> getAllProducts() {
        return queryGateway.query(new GetAllProductsQuery(), ResponseTypes.multipleInstancesOf(Product.class));
    }

    @GetMapping("/products/{id}")
    @Operation(summary = "Get a product by ID")
    public CompletableFuture<Product> getProduct(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), ResponseTypes.instanceOf(Product.class));
    }
}
