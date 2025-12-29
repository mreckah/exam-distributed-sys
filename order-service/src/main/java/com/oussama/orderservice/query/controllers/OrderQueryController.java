package com.oussama.orderservice.query.controllers;

import com.oussama.orderservice.common.api.queries.GetAllOrdersQuery;
import com.oussama.orderservice.common.api.queries.GetOrderByIdQuery;
import com.oussama.orderservice.query.entities.Order;
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
@RequestMapping("/queries/orders")
@AllArgsConstructor
@Tag(name = "Order Queries", description = "Endpoints for querying order data")
public class OrderQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    @Operation(summary = "Get all orders")
    public CompletableFuture<List<Order>> getAllOrders() {
        return queryGateway.query(new GetAllOrdersQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an order by ID")
    public CompletableFuture<Order> getOrder(@PathVariable String id) {
        return queryGateway.query(new GetOrderByIdQuery(id), ResponseTypes.instanceOf(Order.class));
    }
}
