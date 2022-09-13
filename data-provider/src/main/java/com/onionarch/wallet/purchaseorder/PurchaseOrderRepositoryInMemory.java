package com.onionarch.wallet.purchaseorder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderRepositoryInMemory implements PurchaseOrderRepository {
    private final List<PurchaseOrderEntity> memoryDB = new ArrayList<>();
    private final PurchaseOrderDomainToEntityConverter converter;

    public PurchaseOrderRepositoryInMemory(PurchaseOrderDomainToEntityConverter converter) {
        this.converter = converter;
    }

    @Override
    public void save(PurchaseOrder purchaseOrder) {
        var purchaseOrderEntity = converter.convert(purchaseOrder);
        memoryDB.add(purchaseOrderEntity);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        return memoryDB.stream()
                .map(converter::convert)
                .toList();
    }
}
