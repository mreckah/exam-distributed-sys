package com.oussama.orderservice.common.api.commands;

import com.oussama.orderservice.common.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderStatusCommand {
    @TargetAggregateIdentifier
    private String id;
    private OrderStatus status;
}
