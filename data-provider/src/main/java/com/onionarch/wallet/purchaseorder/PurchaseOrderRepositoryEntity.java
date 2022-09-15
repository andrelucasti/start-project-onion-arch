package com.onionarch.wallet.purchaseorder;

import java.util.List;

public interface PurchaseOrderRepositoryEntity {
    PurchaseOrderEntity save(PurchaseOrderEntity purchaseOrderEntity);
    List<PurchaseOrderEntity> findAll();
}
