package com.oussama.orderservice.query.projections;

import com.oussama.orderservice.common.api.queries.GetAllOrdersQuery;
import com.oussama.orderservice.common.api.queries.GetOrderByIdQuery;
import com.oussama.orderservice.query.entities.Order;
import com.oussama.orderservice.query.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderQueryHandler {
    private final OrderRepository orderRepository;

    @QueryHandler
    public Order handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<Order> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }
}
