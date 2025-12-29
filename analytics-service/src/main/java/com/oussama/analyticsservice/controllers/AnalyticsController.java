package com.oussama.analyticsservice.controllers;

import com.oussama.analyticsservice.dto.OrderAnalytics;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.apache.kafka.streams.state.WindowStoreIterator;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final StreamsBuilderFactoryBean streamsBuilderFactoryBean;

    @GetMapping("/orders")
    public Map<String, OrderAnalytics> getAnalytics() {
        KafkaStreams kafkaStreams = streamsBuilderFactoryBean.getKafkaStreams();
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
}
