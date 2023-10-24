package com.herron.exchange.common.api.common.kafka.model;

import com.herron.exchange.common.api.common.api.kafka.KafkaMessageHandler;

public record KafkaSubscriptionRequest(KafkaSubscriptionDetails details, KafkaMessageHandler messageHandler) {
}
