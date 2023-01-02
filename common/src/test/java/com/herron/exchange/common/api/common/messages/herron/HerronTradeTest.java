package com.herron.exchange.common.api.common.messages.herron;

import com.herron.exchange.common.api.common.messages.HerronTrade;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HerronTradeTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronTrade message = factory.manufacturePojo(HerronTrade.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}