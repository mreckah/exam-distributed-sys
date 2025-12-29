package com.oussama.orderservice.common.api.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductProductQuantityCommand {
    private String id;
    private int quantity;
}
