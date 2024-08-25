package com.herron.exchange.common.api.common.websocket;

import com.herron.exchange.common.api.common.websocket.api.WebsocketRequest;
import com.herron.exchange.common.api.common.websocket.api.WebsocketSession;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public abstract class WebsocketHandler {
    private final Map<String, WebsocketSession> uriToSession = new ConcurrentHashMap<>();

    public void subscribe(WebsocketRequest request) {
        uriToSession.computeIfAbsent(request.uri(), uri -> {
                    var session = createSession(request);
                    session.init();
                    return session;
                }
        ).subscribe(request);
    }

    protected abstract WebsocketSession createSession(WebsocketRequest request);

    public void unsubscribe(WebsocketRequest request) {
        Optional.ofNullable(uriToSession.get(request.uri())).ifPresent(session -> session.unsubscribe(request));
    }

    public void close() {
        uriToSession.values().forEach(WebsocketSession::close);
        uriToSession.clear();
    }
}
