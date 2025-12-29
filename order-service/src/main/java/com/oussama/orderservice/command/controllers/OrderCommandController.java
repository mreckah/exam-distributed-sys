package com.oussama.orderservice.command.controllers;

import com.oussama.orderservice.common.api.commands.CreateOrderCommand;
import com.oussama.orderservice.common.api.commands.UpdateOrderStatusCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/orders")
@AllArgsConstructor
@Tag(name = "Order Commands", description = "Endpoints for managing order commands")
public class OrderCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    @Operation(summary = "Create a new order")
    public CompletableFuture<String> createOrder(@RequestBody CreateOrderCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update order status")
    public CompletableFuture<String> updateStatus(@PathVariable String id,
            @RequestBody UpdateOrderStatusCommand command) {
        command.setId(id);
        return commandGateway.send(command);
    }
}
