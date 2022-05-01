package com.binance.api.client.domain.event;

import com.binance.api.client.exception.BinanceApiException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Custom deserializer for a User Data stream event, since the API can return four different responses in this stream.
 *
 * @see UserDataUpdateEvent
 */
public class UserDataUpdateEventDeserializer extends JsonDeserializer<UserDataUpdateEvent> {

    private ObjectMapper mapper;

    @Override
    public UserDataUpdateEvent deserialize(JsonParser jp, DeserializationContext ctx) {
        UserDataUpdateEvent userDataUpdateEvent = new UserDataUpdateEvent();
        try {
            if (mapper == null) {
                mapper = new ObjectMapper();
            }

            ObjectCodec oc = jp.getCodec();
            JsonNode node = oc.readTree(jp);
            String json = node.toString();

            System.out.println(node);

            final String eventTypeId = node.get("e").asText();
            final long eventTime = node.get("E").asLong();
//            {"e":"listenKeyExpired","E":1651367138988}
            UserDataUpdateEventType type = UserDataUpdateEventType.fromEventTypeId(eventTypeId);
            if (type == null) {
                throw new BinanceApiException("Unknown user data event type [" + eventTypeId + "]");
                /*
                 * TODO: need to throws exception on unknown types .
                 *       or ... !?
                 * */
            } else {
                userDataUpdateEvent.setEventType(type);
                userDataUpdateEvent.setEventTime(eventTime);

                switch (type) {
                    case ACCOUNT_UPDATE:
                        if (node.has("a")) {
                            JsonNode a = node.get("a");
                            AccountUpdateEvent accountUpdateEvent = getUserDataUpdateEventDetail(a.toString(), AccountUpdateEvent.class, mapper);
                            userDataUpdateEvent.setAccountUpdateEvent(accountUpdateEvent);
                        }
                        break;
                    case ACCOUNT_POSITION_UPDATE:
                        AccountUpdatePositionEvent accountUpdatePositionEvent = getUserDataUpdateEventDetail(json, AccountUpdatePositionEvent.class, mapper);
                        userDataUpdateEvent.setAccountUpdatePositionEvent(accountUpdatePositionEvent);
                        break;
                    case BALANCE_UPDATE:
                        BalanceUpdateEvent balanceUpdateEvent = getUserDataUpdateEventDetail(json, BalanceUpdateEvent.class, mapper);
                        userDataUpdateEvent.setBalanceUpdateEvent(balanceUpdateEvent);
                        break;
                    case EXECUTION_REPORT:
                        ExecutionReport executionReport = getUserDataUpdateEventDetail(json, ExecutionReport.class, mapper);
                        userDataUpdateEvent.setExecutionReport(executionReport);
                        break;
                    case ORDER_TRADE_UPDATE:
                        JsonNode o = node.get("o");
                        OrderTradeUpdate orderTradeUpdate = getUserDataUpdateEventDetail(o.toString(), OrderTradeUpdate.class, mapper);
                        userDataUpdateEvent.setOrderTradeUpdate(orderTradeUpdate);
                        break;
                    case ACCOUNT_CONFIG_UPDATE:
                        if (node.has("ac")) {
                            JsonNode ac = node.get("ac");
                            LeverageUpdate leverageUpdate = getUserDataUpdateEventDetail(ac.toString(), LeverageUpdate.class, mapper);
                            userDataUpdateEvent.setLeverageUpdate(leverageUpdate);
                        } else if (node.has("ai")) {
                            JsonNode j = node.get("j");
                            AssetsModeChange assetsModeChange = getUserDataUpdateEventDetail(j.toString(), AssetsModeChange.class, mapper);
                            userDataUpdateEvent.setAssetsModeChange(assetsModeChange);
                        }
                        break;
                }

            }

        } catch (Exception ignore) {
            System.err.println("error: " + ignore);
//            ignore.printStackTrace();
            /*
             * NOTE : Do not throws method signature exception .
             * */
        }

        return userDataUpdateEvent;
    }

    public <T> T getUserDataUpdateEventDetail(String json, Class<T> clazz, ObjectMapper mapper) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new BinanceApiException(e);
        }
    }
}
