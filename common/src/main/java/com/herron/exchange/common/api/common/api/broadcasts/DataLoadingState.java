package com.herron.exchange.common.api.common.api.broadcasts;

import com.herron.exchange.common.api.common.api.Event;
import com.herron.exchange.common.api.common.enums.DataLoadingStateEnum;

public interface DataLoadingState extends Event {

    DataLoadingStateEnum state();
}
