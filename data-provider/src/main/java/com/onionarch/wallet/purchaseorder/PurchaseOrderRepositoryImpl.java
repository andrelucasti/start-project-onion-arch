package com.onionarch.wallet.purchaseorder;

import java.util.List;

public class PurchaseOrderRepositoryImpl implements PurchaseOrderRepository{
    private final PurchaseOrderRepositoryEntity purchaseOrderRepositoryEntity;
    private final PurchaseOrderConverter purchaseOrderConverter;

    public PurchaseOrderRepositoryImpl(PurchaseOrderRepositoryEntity purchaseOrderRepositoryEntity, PurchaseOrderConverter purchaseOrderConverter) {
        this.purchaseOrderRepositoryEntity = purchaseOrderRepositoryEntity;
        this.purchaseOrderConverter = purchaseOrderConverter;
    }

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        var purchaseOrderEntity = purchaseOrderConverter.convert(purchaseOrder);
        purchaseOrderRepositoryEntity.save(purchaseOrderEntity);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepositoryEntity.findAll().stream()
                .map(purchaseOrderConverter::convert)
                .toList();
    }
}
