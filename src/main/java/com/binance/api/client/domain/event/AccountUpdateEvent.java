package com.binance.api.client.domain.event;

import com.binance.api.client.domain.BalancePositionReasonType;
import com.binance.api.client.domain.account.Balance;
import com.binance.api.client.domain.account.Position;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author telegram @vonabe on 29.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUpdateEvent {

    @JsonProperty("B")
    @JsonDeserialize(contentUsing = BalanceDeserializer.class)
    private List<Balance> balances;

    @JsonProperty("P")
    @JsonDeserialize(contentUsing = PositionDeserializer.class)
    private List<Position> position;

    @JsonProperty("m")
    private BalancePositionReasonType type;

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public List<Position> getPosition() {
        return position;
    }

    public void setPosition(List<Position> position) {
        this.position = position;
    }

    public BalancePositionReasonType getType() {
        return type;
    }

    public void setType(BalancePositionReasonType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("balances", balances)
                .append("position", position)
                .append("type", type)
                .toString();
    }
}
