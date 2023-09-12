package com.herron.exchange.common.api.common.response;

import com.herron.exchange.common.api.common.messages.response.HerronOrderbookDataResponse;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HerronOrderbookDataResponseTest {
    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronOrderbookDataResponse message = factory.manufacturePojo(HerronOrderbookDataResponse.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}