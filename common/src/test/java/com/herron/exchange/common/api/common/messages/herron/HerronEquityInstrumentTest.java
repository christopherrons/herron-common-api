package com.herron.exchange.common.api.common.messages.herron;

import com.herron.exchange.common.api.common.messages.refdata.HerronEquityInstrument;
import com.herron.exchange.common.api.common.messages.refdata.ImmutableHerronEquityInstrument;
import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


class HerronEquityInstrumentTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronEquityInstrument message = factory.manufacturePojo(ImmutableHerronEquityInstrument.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}