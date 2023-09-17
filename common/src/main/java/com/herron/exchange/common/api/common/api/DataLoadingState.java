package com.herron.exchange.common.api.common.api;

import com.herron.exchange.common.api.common.enums.DataLoadingStateEnum;

public interface DataLoadingState extends Event {

    DataLoadingStateEnum state();
}
