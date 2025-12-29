package com.oussama.orderservice.common.api.events;

import com.oussama.orderservice.common.api.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdatedEvent {
    private String id;
    private OrderStatus status;
}
