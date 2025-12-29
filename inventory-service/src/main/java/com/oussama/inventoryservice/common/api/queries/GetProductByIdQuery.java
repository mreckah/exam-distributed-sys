package com.oussama.inventoryservice.common.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductByIdQuery {
    private String id;
}
