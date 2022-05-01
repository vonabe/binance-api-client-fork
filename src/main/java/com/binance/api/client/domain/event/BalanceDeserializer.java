package com.binance.api.client.domain.event;

import com.binance.api.client.domain.account.Balance;
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
public class BalanceDeserializer extends JsonDeserializer<Balance> {

    @Override
    public Balance deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode jn = codec.readTree(jsonParser);
        String a = jn.get("a").asText();
        double wb = jn.get("wb").asDouble();
        double cw = jn.get("cw").asDouble();
        double bc = jn.get("bc").asDouble();
        return new Balance(a, wb, cw, bc);
    }
}
