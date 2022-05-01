package com.binance.api.client.domain.account;

import com.binance.api.client.domain.MarginType2;
import com.binance.api.client.domain.PositionSide;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author telegram @vonabe on 29.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Position {
    //    {"s":"BTCUSDT","pa":"0","ep":"0.00000","cr":"359.36599004","up":"0","mt":"isolated","iw":"0","ps":"BOTH","ma":"USDT"}
    @JsonProperty("s")
    private String symbol;
    @JsonProperty("pa")
    private Double positionAmount;
    @JsonProperty("ep")
    private Double entryPrice;
    @JsonProperty("cr")
    private Double accumulatedRealized;
    @JsonProperty("up")
    private Double unrealizedPnl;
    @JsonProperty("mt")
    private MarginType2 marginType;
    @JsonProperty("iw")
    private Double isolatedWallet;
    @JsonProperty("ps")
    private PositionSide positionSide;
    @JsonProperty("ma")
    private String ma;

    public Position(String symbol, Double positionAmount, Double entryPrice, Double accumulatedRealized, Double unrealizedPnl, MarginType2 marginType, Double isolatedWallet, PositionSide positionSide, String ma) {
        this.symbol = symbol;
        this.positionAmount = positionAmount;
        this.entryPrice = entryPrice;
        this.accumulatedRealized = accumulatedRealized;
        this.unrealizedPnl = unrealizedPnl;
        this.marginType = marginType;
        this.isolatedWallet = isolatedWallet;
        this.positionSide = positionSide;
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPositionAmount() {
        return positionAmount;
    }

    public void setPositionAmount(Double positionAmount) {
        this.positionAmount = positionAmount;
    }

    public Double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(Double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Double getAccumulatedRealized() {
        return accumulatedRealized;
    }

    public void setAccumulatedRealized(Double accumulatedRealized) {
        this.accumulatedRealized = accumulatedRealized;
    }

    public Double getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public void setUnrealizedPnl(Double unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public MarginType2 getMarginType() {
        return marginType;
    }

    public void setMarginType(MarginType2 marginType) {
        this.marginType = marginType;
    }

    public Double getIsolatedWallet() {
        return isolatedWallet;
    }

    public void setIsolatedWallet(Double isolatedWallet) {
        this.isolatedWallet = isolatedWallet;
    }

    public PositionSide getPositionSide() {
        return positionSide;
    }

    public void setPositionSide(PositionSide positionSide) {
        this.positionSide = positionSide;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("symbol", symbol)
                .append("positionAmount", positionAmount)
                .append("entryPrice", entryPrice)
                .append("accumulatedRealized", accumulatedRealized)
                .append("unrealizedPnl", unrealizedPnl)
                .append("marginType", marginType)
                .append("isolatedWallet", isolatedWallet)
                .append("positionSide", positionSide)
                .append("ma", ma)
                .toString();
    }
}
