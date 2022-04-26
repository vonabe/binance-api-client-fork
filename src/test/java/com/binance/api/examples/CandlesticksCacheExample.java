package com.binance.api.examples;

import com.binance.api.client.BinanceUtils;
import com.binance.api.client.api.BinanceApiWebSocketClient;
import com.binance.api.client.api.sync.BinanceApiSpotRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.factory.BinanceSpotApiClientFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Illustrates how to use the klines/candlesticks event stream to create a local cache of bids/asks for a symbol.
 */
public class CandlesticksCacheExample {

    /**
     * Key is the start/open time of the candle, and the value contains candlestick date.
     */
    private Map<Long, Candlestick> candlesticksCache;

    public CandlesticksCacheExample(String symbol, CandlestickInterval interval) {
        initializeCandlestickCache(symbol, interval);
        startCandlestickEventStreaming(symbol, interval);
    }

    /**
     * Initializes the candlestick cache by using the REST API.
     */
    private void initializeCandlestickCache(String symbol, CandlestickInterval interval) {
        BinanceSpotApiClientFactory factory = BinanceSpotApiClientFactory.newInstance();
        BinanceApiSpotRestClient client = factory.newRestClient();
        List<Candlestick> candlestickBars = client.getCandlestickBars(symbol.toUpperCase(), interval);

        this.candlesticksCache = new TreeMap<>();
        for (Candlestick candlestickBar : candlestickBars) {
            candlesticksCache.put(candlestickBar.getOpenTime(), candlestickBar);
        }
    }

    /**
     * Begins streaming of depth events.
     */
    private void startCandlestickEventStreaming(String symbol, CandlestickInterval interval) {
        BinanceSpotApiClientFactory factory = BinanceSpotApiClientFactory.newInstance();
        BinanceApiWebSocketClient client = factory.newWebSocketClient();

        client.onCandlestickEvent(symbol.toLowerCase(), interval, response -> {
            Long openTime = response.getOpenTime();
            Candlestick updateCandlestick = candlesticksCache.get(openTime);
            if (updateCandlestick == null) {
                // new candlestick
                updateCandlestick = new Candlestick();
            } else {
                // update candlestick with the stream data
                updateCandlestick = BinanceUtils.mapToCandlestick(response);
            }

            // Store the updated candlestick in the cache
            candlesticksCache.put(openTime, updateCandlestick);
            System.out.println(updateCandlestick);
        });
    }

    /**
     * @return a klines/candlestick cache, containing the open/start time of the candlestick as the key,
     * and the candlestick data as the value.
     */
    public Map<Long, Candlestick> getCandlesticksCache() {
        return candlesticksCache;
    }

    public static void main(String[] args) {
        new CandlesticksCacheExample("ETHBTC", CandlestickInterval.ONE_MINUTE);
    }
}
