package com.oussama.orderservice.common.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineRequestDTO {
    private String productId;
    private double price;
    private int quantity;
}
