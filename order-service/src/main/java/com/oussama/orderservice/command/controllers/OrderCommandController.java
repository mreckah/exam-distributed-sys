package com.oussama.orderservice.command.controllers;

import com.oussama.orderservice.common.api.commands.CreateOrderCommand;
import com.oussama.orderservice.common.api.commands.UpdateOrderStatusCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/orders")
@AllArgsConstructor
public class OrderCommandController {
    private final CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody CreateOrderCommand command) {
        command.setId(UUID.randomUUID().toString());
        return commandGateway.send(command);
    }

    @PutMapping("/{id}/status")
    public CompletableFuture<String> updateStatus(@PathVariable String id,
            @RequestBody UpdateOrderStatusCommand command) {
        command.setId(id);
        return commandGateway.send(command);
    }
}
