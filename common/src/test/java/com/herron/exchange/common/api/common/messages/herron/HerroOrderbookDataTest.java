package com.herron.exchange.common.api.common.messages.herron;

import com.herron.exchange.common.api.common.messages.refdata.HerronOrderbookData;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableHerronOrderbookData;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HerroOrderbookDataTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronOrderbookData message = factory.manufacturePojo(ImmutableHerronOrderbookData.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}