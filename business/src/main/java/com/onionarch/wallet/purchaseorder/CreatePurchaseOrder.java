package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.CoinIntegration;


public class CreatePurchaseOrder {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CoinIntegration coinIntegration;

    private CreatePurchaseOrder(PurchaseOrderRepository purchaseOrderRepository, CoinIntegration coinIntegration) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.coinIntegration = coinIntegration;
    }

    private static CreatePurchaseOrder INSTANCE;
    public static CreatePurchaseOrder getInstance(PurchaseOrderRepository purchaseOrderRepository,
                                                  CoinIntegration coinIntegration){
        if (INSTANCE == null) {
            INSTANCE = new CreatePurchaseOrder(purchaseOrderRepository, coinIntegration);
        }

        return INSTANCE;
    }

    public PurchaseOrder execute(PurchaseOrder purchaseOrder) {
        var coin = coinIntegration.fetchCoinBy(purchaseOrder.coin());
        var newPurchaseOrder = new PurchaseOrder(
                purchaseOrder.coin(),
                purchaseOrder.quantity(),
                purchaseOrder.walletId(),
                coin.value(),
                purchaseOrder.purchaseDate()
        );

        return purchaseOrderRepository.save(newPurchaseOrder);
    }
}
