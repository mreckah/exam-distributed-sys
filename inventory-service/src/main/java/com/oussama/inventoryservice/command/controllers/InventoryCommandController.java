package com.oussama.inventoryservice.command.controllers;

import com.oussama.inventoryservice.common.api.commands.CreateCategoryCommand;
import com.oussama.inventoryservice.common.api.commands.CreateProductCommand;
import com.oussama.inventoryservice.common.api.commands.UpdateProductStatusCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/inventory")
@AllArgsConstructor
@Tag(name = "Inventory Commands", description = "Endpoints for managing inventory commands")
public class InventoryCommandController {
    private final CommandGateway commandGateway;

    @PostMapping("/categories")
    @Operation(summary = "Create a new category")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PostMapping("/products")
    @Operation(summary = "Create a new product")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PutMapping("/products/{id}/status")
    @Operation(summary = "Update product status")
    public CompletableFuture<String> updateStatus(@PathVariable String id,
            @RequestBody UpdateProductStatusCommand command) {
        command.setId(id);
        return commandGateway.send(command);
    }
}
