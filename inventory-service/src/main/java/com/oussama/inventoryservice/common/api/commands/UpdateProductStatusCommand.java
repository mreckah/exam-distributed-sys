package com.oussama.inventoryservice.common.api.commands;

import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private InventoryStatus status;
}
