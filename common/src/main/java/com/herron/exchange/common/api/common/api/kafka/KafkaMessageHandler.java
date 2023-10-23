package com.herron.exchange.common.api.common.api.kafka;

import com.herron.exchange.common.api.common.messages.BroadcastMessage;

public interface KafkaMessageHandler {

    void onMessage(BroadcastMessage broadcastMessage);

}
