package com.oussama.inventoryservice.common.api.events;

import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStatusUpdatedEvent {
    private String id;
    private InventoryStatus status;
}
