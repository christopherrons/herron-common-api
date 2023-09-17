package com.herron.exchange.common.api.common.messages.common;

import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HerronDataLoadingTest {

    @Test
    void test_serialize_and_deserialize() {
        PodamFactory factory = new PodamFactoryImpl();
        HerronDataLoading message = factory.manufacturePojo(HerronDataLoading.class);
        String serialized = message.serialize();
        assertEquals(message.getCopy(), message.deserialize(serialized));
    }
}