package com.oussama.orderservice.query.projections;

import com.oussama.orderservice.common.api.enums.OrderStatus;
import com.oussama.orderservice.common.api.events.OrderCreatedEvent;
import com.oussama.orderservice.common.api.events.OrderStatusUpdatedEvent;
import com.oussama.orderservice.query.entities.Order;
import com.oussama.orderservice.query.entities.OrderLine;
import com.oussama.orderservice.query.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class OrderEventHandler {
    private final OrderRepository orderRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = new Order();
        order.setId(event.getId());
        order.setStatus(OrderStatus.CREATED);
        order.setOrderLines(new ArrayList<>());

        event.getOrderLines().forEach(ol -> {
            OrderLine orderLine = new OrderLine();
            orderLine.setProductId(ol.getProductId());
            orderLine.setPrice(ol.getPrice());
            orderLine.setQuantity(ol.getQuantity());
            orderLine.setOrder(order);
            order.getOrderLines().add(orderLine);
        });

        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderStatusUpdatedEvent event) {
        orderRepository.findById(event.getId()).ifPresent(order -> {
            order.setStatus(event.getStatus());
            orderRepository.save(order);
        });
    }
}
