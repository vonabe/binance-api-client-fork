package com.binance.api.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author telegram @vonabe on 28.04.2022.
 * @project binance-api-client
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public enum BalancePositionReasonType {
    DEPOSIT,
    WITHDRAW,
    ORDER,
    FUNDING_FEE,
    WITHDRAW_REJECT,
    ADJUSTMENT,
    INSURANCE_CLEAR,
    ADMIN_DEPOSIT,
    ADMIN_WITHDRAW,
    MARGIN_TRANSFER,
    MARGIN_TYPE_CHANGE,
    ASSET_TRANSFER,
    OPTIONS_PREMIUM_FEE,
    OPTIONS_SETTLE_PROFIT,
    AUTO_EXCHANGE
}
