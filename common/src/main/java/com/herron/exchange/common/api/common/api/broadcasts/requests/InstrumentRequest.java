package com.herron.exchange.common.api.common.api.broadcasts.requests;

import com.herron.exchange.common.api.common.api.referencedata.instruments.Instrument;

public interface InstrumentRequest extends Request {

    Instrument instrument();
}
