package com.oussama.inventoryservice.common.api.events;

import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private InventoryStatus status;
    private String categoryId;
}
