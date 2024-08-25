package com.herron.exchange.common.api.common.websocket.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herron.exchange.common.api.common.websocket.WebsocketMessageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public abstract class WebsocketSession implements WebSocket.Listener {
    private final Logger logger = LoggerFactory.getLogger(WebsocketSession.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    protected final URI uri;
    protected final Map<WebsocketRequestKey, Consumer<Object>> requestKeyToConsumer = new HashMap<>();
    private final AtomicReference<WebSocket> webSocket = new AtomicReference<>();

    protected WebsocketSession(String uri) {
        this.uri = URI.create(uri);
    }

    public void init() {
        logger.info("Attempting to connect to: {}.", uri);
        HttpClient.newHttpClient()
                .newWebSocketBuilder()
                .buildAsync(uri, this)
                .join();
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        logger.info("WebSocket connection opened.");
        this.webSocket.set(webSocket);
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        extractWrapper(data).ifPresent(wrapper -> requestKeyToConsumer.get(wrapper.key()).accept(wrapper.data()));
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    protected abstract Optional<WebsocketMessageWrapper> extractWrapper(CharSequence data);

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket,
                                      int statusCode,
                                      String reason) {
        logger.info("Closing Session.");
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        logger.error("WebSocket error: {}", error.getMessage());
    }

    public void subscribe(WebsocketRequest request) {
        requestKeyToConsumer.putIfAbsent(request.createKey(), request.consumer());
        sendMessage(request.createRequestMessage());
    }

    public void unsubscribe(WebsocketRequest request) {
        sendMessage(request.createRequestMessage());
        requestKeyToConsumer.remove(request.createKey());
    }

    public void close() {
        Optional.ofNullable(webSocket.get()).ifPresent(ws -> ws.sendClose(WebSocket.NORMAL_CLOSURE, "ok"));
    }

    private <T> void sendMessage(T message) {
        try {
            var request = MAPPER.writeValueAsString(message);
            Optional.ofNullable(webSocket.get()).ifPresent(ws -> {
                ws.sendText(request, true);
                logger.info("Send message: {}", request);
            });
        } catch (Exception e) {
            logger.error("Error sending message: {}", e.getMessage(), e);
        }
    }
}
