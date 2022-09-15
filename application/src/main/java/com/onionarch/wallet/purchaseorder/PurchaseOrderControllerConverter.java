package com.onionarch.wallet.purchaseorder;

import org.springframework.stereotype.Component;

@Component
public class PurchaseOrderControllerConverter {

    public PurchaseOrder convert(PurchaseOrderRequest purchaseOrderRequest){
        return new PurchaseOrder(
                purchaseOrderRequest.coinSymbol(),
                purchaseOrderRequest.coinAmount(),
                purchaseOrderRequest.walletId());
    }

    public PurchaseOrderResponse convert(PurchaseOrder purchaseOrder){
        return new PurchaseOrderResponse(
                purchaseOrder.walletId(),
                purchaseOrder.coin(),
                purchaseOrder.quantity(),
                purchaseOrder.purchaseValue(),
                purchaseOrder.purchaseDate());
    }
}
