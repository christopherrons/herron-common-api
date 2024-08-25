package com.herron.exchange.common.api.common.websocket;

import com.herron.exchange.common.api.common.websocket.api.WebsocketRequestKey;

public record WebsocketMessageWrapper(WebsocketRequestKey key, Object data) {
}
