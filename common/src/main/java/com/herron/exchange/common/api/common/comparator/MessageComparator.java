package com.herron.exchange.common.api.common.comparator;

import com.herron.exchange.common.api.common.api.Message;

import java.util.Comparator;

public class MessageComparator<T extends Message> implements Comparator<T> {
    @Override
    public int compare(T message, T otherMessage) {
        if (message.timeStampInMs() < otherMessage.timeStampInMs()) {
            return -1;
        }
        if (message.timeStampInMs() > otherMessage.timeStampInMs()) {
            return 1;
        }

        return 0;
    }
}
