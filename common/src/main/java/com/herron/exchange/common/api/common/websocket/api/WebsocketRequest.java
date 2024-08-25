package com.herron.exchange.common.api.common.websocket.api;

import java.util.function.Consumer;

public interface WebsocketRequest {

    String uri();

    String topic();

    Consumer<Object> consumer();

    Object createRequestMessage();

    default WebsocketRequestKey createKey() {
        return new DefaultRequestKey(uri(), topic());
    }

    record DefaultRequestKey(String uri, String topic) implements WebsocketRequestKey {
    }
}
