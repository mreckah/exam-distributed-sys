package com.oussama.inventoryservice.common.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String description;
}
