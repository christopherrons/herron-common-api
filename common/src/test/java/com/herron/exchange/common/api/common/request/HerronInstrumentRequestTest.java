package com.herron.exchange.common.api.common.request;

import com.herron.exchange.common.api.common.messages.request.HerronInstrumentRequest;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HerronInstrumentRequestTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronInstrumentRequest message = factory.manufacturePojo(HerronInstrumentRequest.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}