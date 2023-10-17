package com.herron.exchange.common.api.common.api.broadcasts;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.api.Message;
import com.herron.exchange.common.api.common.messages.common.PartitionKey;

public interface BroadcastMessage extends Event {

    long sequenceNumber();

    Message message();

    PartitionKey partitionKey();

}
