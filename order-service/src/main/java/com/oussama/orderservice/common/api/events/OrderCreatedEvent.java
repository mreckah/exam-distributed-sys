package com.oussama.orderservice.common.api.events;

import com.oussama.orderservice.common.api.models.OrderLineRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private String id;
    private List<OrderLineRequestDTO> orderLines;
}
