package com.herron.exchange.common.api.common.api.broadcasts;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.api.Message;

public interface BroadcastMessage extends Event {

    long sequenceNumber();

    Message message();

}
