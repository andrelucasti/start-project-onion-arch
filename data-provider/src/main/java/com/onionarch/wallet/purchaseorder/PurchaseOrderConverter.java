package com.onionarch.wallet.purchaseorder;

public class PurchaseOrderConverter {

    public PurchaseOrderEntity convert(PurchaseOrder purchaseOrder){
        return new PurchaseOrderEntity(
                purchaseOrder.walletId(),
                purchaseOrder.coin(),
                purchaseOrder.quantity(),
                purchaseOrder.purchaseValue(),
                purchaseOrder.purchaseDate());
    }

    public PurchaseOrder convert(PurchaseOrderEntity purchaseOrderEntity) {
        return new PurchaseOrder(
                purchaseOrderEntity.getCoin(),
                purchaseOrderEntity.getQuantity(),
                purchaseOrderEntity.getWalletId(),
                purchaseOrderEntity.getPurchaseValue(),
                purchaseOrderEntity.getPurchaseDate());
    }
}
