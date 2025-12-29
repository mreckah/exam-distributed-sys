package com.oussama.inventoryservice.common.api.commands;

import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private double price;
    private int quantity;
    private InventoryStatus status;
    private String categoryId;
}
