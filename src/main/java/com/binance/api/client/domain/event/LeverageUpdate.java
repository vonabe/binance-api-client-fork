package com.binance.api.client.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author telegram @vonabe on 24.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeverageUpdate {

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("l")
    private Long leverage;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getLeverage() {
        return leverage;
    }

    public void setLeverage(Long leverage) {
        this.leverage = leverage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("leverage", leverage)
                .toString();
    }
}
