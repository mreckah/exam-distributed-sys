package com.oussama.orderservice.sagas;

import com.oussama.orderservice.common.api.commands.DeductProductQuantityCommand;
import com.oussama.orderservice.common.api.commands.UpdateOrderStatusCommand;
import com.oussama.orderservice.common.api.enums.OrderStatus;
import com.oussama.orderservice.common.api.events.OrderCreatedEvent;
import com.oussama.orderservice.common.api.events.OrderStatusUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga
@Slf4j
public class OrderSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderCreatedEvent event) {
        log.info("OrderCreatedEvent in Saga for orderId: {}", event.getId());
        // For each order line, deduct stock
        event.getOrderLines().forEach(ol -> {
            commandGateway.send(new DeductProductQuantityCommand(ol.getProductId(), ol.getQuantity()));
        });

        // Update order status to VALIDATED (simplified)
        commandGateway.send(new UpdateOrderStatusCommand(event.getId(), OrderStatus.ACTIVATED));
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderStatusUpdatedEvent event) {
        log.info("OrderStatusUpdatedEvent in Saga for orderId: {}", event.getId());
    }
}
