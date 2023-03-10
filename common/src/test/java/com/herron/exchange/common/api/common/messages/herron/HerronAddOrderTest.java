package com.herron.exchange.common.api.common.messages.herron;

import com.herron.exchange.common.api.common.messages.HerronAddOrder;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HerronAddOrderTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronAddOrder message = factory.manufacturePojo(HerronAddOrder.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}