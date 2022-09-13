package com.onionarch.wallet.purchaseorder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PurchaseOrderRepositoryInMemory implements PurchaseOrderRepositoryEntity {
    private final List<PurchaseOrderEntity> memoryDB = new ArrayList<>();

    @Override
    public void save(PurchaseOrderEntity purchaseOrderEntity) {
        memoryDB.add(purchaseOrderEntity);
    }

    @Override
    public List<PurchaseOrderEntity> findAll() {
        return memoryDB;
    }
}
