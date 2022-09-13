package com.onionarch.wallet.purchaseorder;

public class PurchaseOrderDomainToEntityConverter {

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
                purchaseOrderEntity.coin(),
                purchaseOrderEntity.quantity(),
                purchaseOrderEntity.walletId(),
                purchaseOrderEntity.purchaseValue(),
                purchaseOrderEntity.purchaseDate());
    }
}
