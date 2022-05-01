package com.binance.api.client.domain.event;

import com.binance.api.client.domain.MarginType2;
import com.binance.api.client.domain.PositionSide;
import com.binance.api.client.domain.account.Position;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * @author telegram @vonabe on 28.04.2022.
 * @project binance-api-client
 */
public class PositionDeserializer extends JsonDeserializer<Position> {

    @Override
    public Position deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jn = codec.readTree(jsonParser);
//        {"s":"BTCUSDT","pa":"0","ep":"0.00000","cr":"359.36599004","up":"0","mt":"isolated","iw":"0","ps":"BOTH","ma":"USDT"}
        String symbol = jn.get("s").asText();
        double positionAmount = jn.get("pa").asDouble();
        double entryPrice = jn.get("ep").asDouble();
        double accumulatedRealized = jn.get("cr").asDouble();
        double unrealizedPnl = jn.get("up").asDouble();
        MarginType2 marginType = MarginType2.valueOf(jn.get("mt").textValue().toUpperCase());
        double isolatedWallet = jn.get("iw").asDouble();
        PositionSide positionSide = PositionSide.valueOf(jn.get("ps").textValue());
        String ma = jn.get("ma").asText();
        return new Position(symbol, positionAmount, entryPrice, accumulatedRealized, unrealizedPnl, marginType, isolatedWallet, positionSide, ma);
    }
}
