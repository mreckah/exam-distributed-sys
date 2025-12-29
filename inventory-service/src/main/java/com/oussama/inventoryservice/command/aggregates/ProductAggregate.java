package com.oussama.inventoryservice.command.aggregates;

import com.oussama.inventoryservice.common.api.commands.CreateProductCommand;
import com.oussama.inventoryservice.common.api.commands.DeductProductQuantityCommand;
import com.oussama.inventoryservice.common.api.commands.UpdateProductStatusCommand;
import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import com.oussama.inventoryservice.common.api.events.ProductCreatedEvent;
import com.oussama.inventoryservice.common.api.events.ProductQuantityDeductedEvent;
import com.oussama.inventoryservice.common.api.events.ProductStatusUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private double price;
    private int quantity;
    private InventoryStatus status;
    private String categoryId;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getQuantity(),
                command.getStatus(),
                command.getCategoryId()));
    }

    @CommandHandler
    public void handle(UpdateProductStatusCommand command) {
        AggregateLifecycle.apply(new ProductStatusUpdatedEvent(
                command.getId(),
                command.getStatus()));
    }

    @CommandHandler
    public void handle(DeductProductQuantityCommand command) {
        if (this.quantity < command.getQuantity()) {
            throw new RuntimeException("Insufficient quantity for product: " + command.getId());
        }
        AggregateLifecycle.apply(new ProductQuantityDeductedEvent(
                command.getId(),
                command.getQuantity()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.status = event.getStatus();
        this.categoryId = event.getCategoryId();
    }

    @EventSourcingHandler
    public void on(ProductStatusUpdatedEvent event) {
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(ProductQuantityDeductedEvent event) {
        this.quantity -= event.getQuantity();
    }
}
