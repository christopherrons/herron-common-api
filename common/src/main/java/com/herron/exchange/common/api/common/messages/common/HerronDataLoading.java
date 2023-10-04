package com.herron.exchange.common.api.common.messages.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.herron.exchange.common.api.common.api.DataLoadingState;
import com.herron.exchange.common.api.common.enums.DataLoadingStateEnum;
import com.herron.exchange.common.api.common.enums.MessageTypesEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public record HerronDataLoading(long timeStampInMs, DataLoadingStateEnum state) implements DataLoadingState {

    public HerronDataLoading(DataLoadingState dataLoadingState) {
        this(dataLoadingState.timeStampInMs(), dataLoadingState.state());
    }

    @Override
    public MessageTypesEnum messageType() {
        return MessageTypesEnum.HERRON_DATA_LOADING_START;
    }
}
