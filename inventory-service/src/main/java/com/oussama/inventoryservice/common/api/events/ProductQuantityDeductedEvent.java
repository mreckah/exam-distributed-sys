package com.oussama.inventoryservice.common.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQuantityDeductedEvent {
    private String id;
    private int quantity;
}
