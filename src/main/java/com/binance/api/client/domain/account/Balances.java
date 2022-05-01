package com.binance.api.client.domain.account;

import com.binance.api.client.domain.event.BalanceDeserializer;
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
public class Balances {
    @JsonProperty("B")
    @JsonDeserialize(contentUsing = BalanceDeserializer.class)
    private List<Balance> balances;

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("balances", balances)
                .toString();
    }
}
