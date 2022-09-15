package com.onionarch.wallet.purchaseorder;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class PurchaseOrderRepositorySpringData implements PurchaseOrderRepositoryEntity {

    private final PurchaseOrderEntityJPA purchaseOrderEntityJPA;

    public PurchaseOrderRepositorySpringData(PurchaseOrderEntityJPA purchaseOrderEntityJPA) {
        this.purchaseOrderEntityJPA = purchaseOrderEntityJPA;
    }

    @Override
    @Transactional
    public PurchaseOrderEntity save(PurchaseOrderEntity purchaseOrderEntity) {
        return purchaseOrderEntityJPA.save(purchaseOrderEntity);
    }

    @Override
    @Transactional
    public List<PurchaseOrderEntity> findAll() {
        return StreamSupport
                .stream(purchaseOrderEntityJPA
                        .findAll()
                        .spliterator(), false)
                .toList();
    }
}
