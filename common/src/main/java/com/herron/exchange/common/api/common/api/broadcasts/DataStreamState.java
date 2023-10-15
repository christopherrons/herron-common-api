package com.herron.exchange.common.api.common.api.broadcasts;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.DataStreamEnum;

public interface DataStreamState extends Event {

    DataStreamEnum state();
}
