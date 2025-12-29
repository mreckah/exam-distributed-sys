package com.oussama.analyticsservice.config;

import com.oussama.analyticsservice.dto.OrderAnalytics;
import com.oussama.analyticsservice.dto.OrderEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Bean
    public KStream<String, OrderEvent> kStream(StreamsBuilder builder) {
        JsonSerde<OrderEvent> orderEventSerde = new JsonSerde<>(OrderEvent.class);
        JsonSerde<OrderAnalytics> analyticsSerde = new JsonSerde<>(OrderAnalytics.class);

        KStream<String, OrderEvent> stream = builder.stream("orders", Consumed.with(Serdes.String(), orderEventSerde));

        stream.groupBy((key, value) -> "all-orders", Grouped.with(Serdes.String(), orderEventSerde))
                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(5)))
                .aggregate(
                        () -> new OrderAnalytics(0L, 0.0),
                        (key, value, aggregate) -> {
                            aggregate.setCount(aggregate.getCount() + 1);
                            aggregate.setTotalAmount(
                                    aggregate.getTotalAmount() + (value.getPrice() * value.getQuantity()));
                            return aggregate;
                        },
                        Materialized.<String, OrderAnalytics, WindowStore<org.apache.kafka.common.utils.Bytes, byte[]>>as(
                                "order-analytics-store")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(analyticsSerde));

        return stream;
    }
}
