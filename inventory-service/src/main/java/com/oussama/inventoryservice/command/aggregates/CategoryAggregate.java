package com.oussama.inventoryservice.command.aggregates;

import com.oussama.inventoryservice.common.api.commands.CreateCategoryCommand;
import com.oussama.inventoryservice.common.api.events.CategoryCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public CategoryAggregate() {
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription()));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
