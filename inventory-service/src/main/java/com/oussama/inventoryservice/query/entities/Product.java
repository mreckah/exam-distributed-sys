package com.oussama.inventoryservice.query.entities;

import com.oussama.inventoryservice.common.api.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private InventoryStatus status;
    @ManyToOne
    private Category category;
}
