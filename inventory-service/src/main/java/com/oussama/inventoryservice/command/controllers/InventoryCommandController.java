package com.oussama.inventoryservice.command.controllers;

import com.oussama.inventoryservice.common.api.commands.CreateCategoryCommand;
import com.oussama.inventoryservice.common.api.commands.CreateProductCommand;
import com.oussama.inventoryservice.common.api.commands.UpdateProductStatusCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/inventory")
@AllArgsConstructor
public class InventoryCommandController {
    private final CommandGateway commandGateway;

    @PostMapping("/categories")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PostMapping("/products")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PutMapping("/products/{id}/status")
    public CompletableFuture<String> updateStatus(@PathVariable String id,
            @RequestBody UpdateProductStatusCommand command) {
        command.setId(id);
        return commandGateway.send(command);
    }
}
