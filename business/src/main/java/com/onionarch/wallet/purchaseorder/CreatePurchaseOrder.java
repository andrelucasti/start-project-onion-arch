package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.CoinIntegration;

public record CreatePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, CoinIntegration coinIntegration) {
    public void execute(PurchaseOrder purchaseOrder) {
        var coin = coinIntegration.fetchCoinBy(purchaseOrder.coin());
        var newPurchaseOrder = new PurchaseOrder(
                purchaseOrder.coin(),
                purchaseOrder.quantity(),
                purchaseOrder.walletId(),
                coin.value(),
                purchaseOrder.purchaseDate()
        );

        purchaseOrderRepository.save(newPurchaseOrder);
    }
}
