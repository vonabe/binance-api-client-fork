package com.binance.api.client.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author telegram @vonabe on 28.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Balance {
    @JsonProperty("a")
    private String asset;
    @JsonProperty("wb")
    private double wallet_balance;
    @JsonProperty("cw")
    private double cross_wallet_balance;
    @JsonProperty("bc")
    private double change_balance_noPnl_noFee;

    public Balance(String asset, double wallet_balance, double cross_wallet_balance, double change_balance_noPnl_noFee) {
        this.asset = asset;
        this.wallet_balance = wallet_balance;
        this.cross_wallet_balance = cross_wallet_balance;
        this.change_balance_noPnl_noFee = change_balance_noPnl_noFee;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public double getWallet_balance() {
        return wallet_balance;
    }

    public void setWallet_balance(double wallet_balance) {
        this.wallet_balance = wallet_balance;
    }

    public double getCross_wallet_balance() {
        return cross_wallet_balance;
    }

    public void setCross_wallet_balance(double cross_wallet_balance) {
        this.cross_wallet_balance = cross_wallet_balance;
    }

    public double getChange_balance_noPnl_noFee() {
        return change_balance_noPnl_noFee;
    }

    public void setChange_balance_noPnl_noFee(double change_balance_noPnl_noFee) {
        this.change_balance_noPnl_noFee = change_balance_noPnl_noFee;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("asset", asset)
                .append("wallet_balance", wallet_balance)
                .append("cross_wallet_balance", cross_wallet_balance)
                .append("change_balance_noPnl_noFee", change_balance_noPnl_noFee)
                .toString();
    }

}
