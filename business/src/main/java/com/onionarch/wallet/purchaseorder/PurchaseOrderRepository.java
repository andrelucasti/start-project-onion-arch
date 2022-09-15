package com.onionarch.wallet.purchaseorder;

import java.util.List;

public interface PurchaseOrderRepository {
    PurchaseOrder save(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> findAll();
}
