package com.onionarch.wallet.purchaseorder;

import java.util.List;

public interface PurchaseOrderRepositoryEntity {
    void save(PurchaseOrderEntity purchaseOrderEntity);
    List<PurchaseOrderEntity> findAll();
}
