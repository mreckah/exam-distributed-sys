package com.oussama.orderservice.query.controllers;

import com.oussama.orderservice.common.api.queries.GetAllOrdersQuery;
import com.oussama.orderservice.common.api.queries.GetOrderByIdQuery;
import com.oussama.orderservice.query.entities.Order;
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
public class OrderQueryController {
    private final QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<Order>> getAllOrders() {
        return queryGateway.query(new GetAllOrdersQuery(), ResponseTypes.multipleInstancesOf(Order.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<Order> getOrder(@PathVariable String id) {
        return queryGateway.query(new GetOrderByIdQuery(id), ResponseTypes.instanceOf(Order.class));
    }
}
