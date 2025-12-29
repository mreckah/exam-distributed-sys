package com.oussama.orderservice.command.aggregates;

import com.oussama.orderservice.common.api.commands.CreateOrderCommand;
import com.oussama.orderservice.common.api.commands.UpdateOrderStatusCommand;
import com.oussama.orderservice.common.api.enums.OrderStatus;
import com.oussama.orderservice.common.api.events.OrderCreatedEvent;
import com.oussama.orderservice.common.api.events.OrderStatusUpdatedEvent;
import com.oussama.orderservice.common.api.models.OrderLineRequestDTO;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String id;
    private OrderStatus status;
    private List<OrderLineRequestDTO> orderLines;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        AggregateLifecycle.apply(new OrderCreatedEvent(
                command.getId(),
                command.getOrderLines()));
    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand command) {
        AggregateLifecycle.apply(new OrderStatusUpdatedEvent(
                command.getId(),
                command.getStatus()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.orderLines = event.getOrderLines();
        this.status = OrderStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(OrderStatusUpdatedEvent event) {
        this.status = event.getStatus();
    }
}
