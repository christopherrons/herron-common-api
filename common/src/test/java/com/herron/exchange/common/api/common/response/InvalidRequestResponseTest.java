package com.herron.exchange.common.api.common.response;

import com.herron.exchange.common.api.common.messages.response.InvalidRequestResponse;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

class InvalidRequestResponseTest {
    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        InvalidRequestResponse message = factory.manufacturePojo(InvalidRequestResponse.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}