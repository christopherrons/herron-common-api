package com.herron.exchange.common.api.common.messages.herron;

import com.herron.exchange.common.api.common.api.BroadcastMessage;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;
import com.herron.exchange.common.api.common.messages.HerronAddOrder;
import com.herron.exchange.common.api.common.messages.HerronBroadcastMessage;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HerronBroadcastMessageTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronAddOrder message = factory.manufacturePojo(HerronAddOrder.class);
        HerronBroadcastMessage broadcastMessage = new HerronBroadcastMessage(message, MessageTypesEnum.HERRON_ADD_ORDER.getMessageTypeId(), 1, 0);
        String serialized = broadcastMessage.serialize();
        assertEquals(message.getCopy(), ((BroadcastMessage) broadcastMessage.deserialize(serialized)).message());
        assertEquals(broadcastMessage.getCopy(), broadcastMessage.deserialize(serialized));
    }
}