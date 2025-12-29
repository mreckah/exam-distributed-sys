package com.oussama.inventoryservice.common.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreatedEvent {
    private String id;
    private String name;
    private String description;
}
