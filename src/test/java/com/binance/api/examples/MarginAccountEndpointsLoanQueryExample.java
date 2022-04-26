package com.binance.api.examples;

import com.binance.api.client.api.sync.BinanceApiMarginRestClient;
import com.binance.api.client.domain.account.MarginTransaction;
import com.binance.api.client.domain.account.MaxBorrowableQueryResult;
import com.binance.api.client.domain.account.RepayQueryResult;
import com.binance.api.client.factory.BinanceAbstractFactory;
import com.binance.api.client.factory.BinanceMarginApiClientFactory;

/**
 * Examples on how to get margin account information.
 */
public class MarginAccountEndpointsLoanQueryExample {

    public static void main(String[] args) {
        BinanceMarginApiClientFactory factory = BinanceAbstractFactory.createMarginFactory("YOUR_API_KEY", "YOUR_SECRET");
        BinanceApiMarginRestClient client = factory.newRestClient();
        MaxBorrowableQueryResult usdt = client.queryMaxBorrowable("USDT");
        System.out.println(usdt.getAmount());
        MaxBorrowableQueryResult bnb = client.queryMaxBorrowable("BNB");
        System.out.println(bnb.getAmount());
        MarginTransaction borrowed = client.borrow("USDT", "310");
        System.out.println(borrowed.getTranId());
        MarginTransaction repaid = client.repay("USDT", "310");
        System.out.println(repaid);
        RepayQueryResult repayQueryResult = client.queryRepay("BNB", System.currentTimeMillis() - 1000);
        System.out.println(repayQueryResult);
    }
}
