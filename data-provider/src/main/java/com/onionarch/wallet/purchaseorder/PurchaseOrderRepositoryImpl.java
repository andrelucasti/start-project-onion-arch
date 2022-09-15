package com.onionarch.wallet.purchaseorder;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository{
    private final PurchaseOrderRepositoryEntity purchaseOrderRepositoryEntity;
    private final PurchaseOrderConverter purchaseOrderConverter;

    public PurchaseOrderRepositoryImpl(@Qualifier("purchaseOrderRepositoryInMemory") PurchaseOrderRepositoryEntity purchaseOrderRepositoryEntity,
                                       PurchaseOrderConverter purchaseOrderConverter) {
        this.purchaseOrderRepositoryEntity = purchaseOrderRepositoryEntity;
        this.purchaseOrderConverter = purchaseOrderConverter;
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        var purchaseOrderEntity = purchaseOrderConverter.convert(purchaseOrder);
        return purchaseOrderConverter.convert(purchaseOrderRepositoryEntity.save(purchaseOrderEntity));
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepositoryEntity.findAll().stream()
                .map(purchaseOrderConverter::convert)
                .toList();
    }
}
