package com.oussama.analyticsservice.controllers;

import com.oussama.analyticsservice.dto.OrderAnalytics;
import com.oussama.analyticsservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @GetMapping("/orders")
    public Map<String, OrderAnalytics> getAnalytics() {
        KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
        if (kafkaStreams == null || kafkaStreams.state() != KafkaStreams.State.RUNNING) {
            return new HashMap<>();
        }
        ReadOnlyWindowStore<String, OrderAnalytics> store = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("order-analytics-store", QueryableStoreTypes.windowStore()));

        Instant now = Instant.now();
        Instant from = now.minusSeconds(60);
        WindowStoreIterator<OrderAnalytics> iterator = store.fetch("all-orders", from, now);

        Map<String, OrderAnalytics> results = new HashMap<>();
        while (iterator.hasNext()) {
            var next = iterator.next();
            results.put(Instant.ofEpochMilli(next.key).toString(), next.value);
        }
        return results;
    }

    @PostMapping("/test/orders")
    public String publishTestOrderEvent(@RequestBody(required = false) OrderEvent event) {
        OrderEvent toSend = event;
        if (toSend == null) {
            toSend = new OrderEvent(UUID.randomUUID().toString(), "prod-1", 1, 10.0, "CREATED");
        } else if (toSend.getId() == null || toSend.getId().isBlank()) {
            toSend.setId(UUID.randomUUID().toString());
        }
        kafkaTemplate.send("orders", toSend.getId(), toSend);
        return toSend.getId();
    }
}
