package com.oussama.orderservice.common.api.commands;

import com.oussama.orderservice.common.api.models.OrderLineRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private String id;
    private List<OrderLineRequestDTO> orderLines;
}
