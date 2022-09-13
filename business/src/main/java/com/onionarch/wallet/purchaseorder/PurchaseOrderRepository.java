package com.onionarch.wallet.purchaseorder;

import java.util.List;

public interface PurchaseOrderRepository {
    void save(PurchaseOrder purchaseOrder);

    List<PurchaseOrder> findAll();
}
