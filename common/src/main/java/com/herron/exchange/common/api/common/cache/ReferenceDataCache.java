package com.herron.exchange.common.api.common.cache;

import com.herron.exchange.common.api.common.api.Instrument;
import com.herron.exchange.common.api.common.api.Market;
import com.herron.exchange.common.api.common.api.OrderbookData;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReferenceDataCache {
    private static ReferenceDataCache instance;
    private final Map<String, Instrument> instrumentIdToInstrument = new ConcurrentHashMap<>();
    private final Map<String, OrderbookData> orderbookIdToOrderbookData = new ConcurrentHashMap<>();
    private final Map<String, Market> marketIdToMarket = new ConcurrentHashMap<>();

    private ReferenceDataCache() {
    }

    public static synchronized ReferenceDataCache getCache() {
        if (instance == null)
            instance = new ReferenceDataCache();

        return instance;
    }

    public void addMarket(Market market) {
        marketIdToMarket.put(market.marketId(), market);
    }

    public void addInstrument(Instrument instrument) {
        instrumentIdToInstrument.put(instrument.instrumentId(), instrument);
    }

    public void addOrderbookData(OrderbookData orderbookData) {
        orderbookIdToOrderbookData.put(orderbookData.orderbookId(), orderbookData);
    }

    public Collection<Instrument> getInstruments() {
        return instrumentIdToInstrument.values();
    }

    public Collection<OrderbookData> getOrderbookData() {
        return orderbookIdToOrderbookData.values();
    }

    public Collection<Market> getMarkets() {
        return marketIdToMarket.values();
    }

    public Instrument getInstrument(String instrumentId) {
        return instrumentIdToInstrument.get(instrumentId);
    }

    public OrderbookData getOrderbookData(String orderbookId) {
        return orderbookIdToOrderbookData.get(orderbookId);
    }

    public Market getMarket(String marketId) {
        return marketIdToMarket.get(marketId);
    }
}
