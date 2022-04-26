package com.binance.api.client.impl.async;

import com.binance.api.client.api.async.BinanceApiAsyncMarginRestClient;
import com.binance.api.client.api.BinanceApiCallback;
import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.TransferType;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.CancelOrderResponse;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.event.ListenKey;
import com.binance.api.client.impl.BinanceApiCallbackAdapter;
import com.binance.api.client.api.BinanceApiService;
import com.binance.api.client.impl.BinanceApiServiceGenerator;

import java.util.List;


/**
 * Implementation of Binance's Margin REST API using Retrofit with asynchronous/non-blocking method calls.
 */
public class BinanceApiAsyncMarginRestClientImpl implements BinanceApiAsyncMarginRestClient {

    private final BinanceApiService binanceApiService;

    public BinanceApiAsyncMarginRestClientImpl(String apiKey, String secret, String apiUrl) {
        binanceApiService = BinanceApiServiceGenerator.createService(BinanceApiService.class, apiKey, secret, apiUrl);
    }

    // Margin Account endpoints

    @Override
    public void getAccount(Long recvWindow, Long timestamp, BinanceApiCallback<MarginAccount> callback) {
        binanceApiService.getMarginAccount(recvWindow, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getAccount(BinanceApiCallback<MarginAccount> callback) {
        long timestamp = System.currentTimeMillis();
        binanceApiService.getMarginAccount(BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOpenOrders(OrderRequest orderRequest, BinanceApiCallback<List<Order>> callback) {
        binanceApiService.getOpenMarginOrders(orderRequest.getSymbol(), orderRequest.getRecvWindow(),
                orderRequest.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void newOrder(MarginNewOrder order, BinanceApiCallback<MarginNewOrderResponse> callback) {
        binanceApiService.newMarginOrder(order.getSymbol(), order.getSide(), order.getType(), order.getTimeInForce(),
                order.getQuantity(), order.getPrice(), order.getNewClientOrderId(), order.getStopPrice(), order.getIcebergQty(),
                order.getNewOrderRespType(), order.getSideEffectType(), order.getRecvWindow(), order.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void cancelOrder(CancelOrderRequest cancelOrderRequest, BinanceApiCallback<CancelOrderResponse> callback) {
        binanceApiService.cancelMarginOrder(cancelOrderRequest.getSymbol(),
                cancelOrderRequest.getOrderId(), cancelOrderRequest.getOrigClientOrderId(), cancelOrderRequest.getNewClientOrderId(),
                cancelOrderRequest.getRecvWindow(), cancelOrderRequest.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getOrderStatus(OrderStatusRequest orderStatusRequest, BinanceApiCallback<Order> callback) {
        binanceApiService.getMarginOrderStatus(orderStatusRequest.getSymbol(),
                orderStatusRequest.getOrderId(), orderStatusRequest.getOrigClientOrderId(),
                orderStatusRequest.getRecvWindow(), orderStatusRequest.getTimestamp()).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    // user stream endpoints

    @Override
    public void startUserDataStream(BinanceApiCallback<ListenKey> callback) {
        binanceApiService.startMarginUserDataStream().enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void keepAliveUserDataStream(String listenKey, BinanceApiCallback<Void> callback) {
        binanceApiService.keepAliveMarginUserDataStream(listenKey).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void transfer(String asset, String amount, TransferType type, BinanceApiCallback<MarginTransaction> callback) {
        long timestamp = System.currentTimeMillis();
        binanceApiService.transfer(asset, amount, type.getValue(), BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void borrow(String asset, String amount, BinanceApiCallback<MarginTransaction> callback) {
        long timestamp = System.currentTimeMillis();
        binanceApiService.borrow(asset, amount, BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void repay(String asset, String amount, BinanceApiCallback<MarginTransaction> callback) {
        long timestamp = System.currentTimeMillis();
        binanceApiService.repay(asset, amount, BinanceApiConstants.DEFAULT_MARGIN_RECEIVING_WINDOW, timestamp).enqueue(new BinanceApiCallbackAdapter<>(callback));
    }
}
