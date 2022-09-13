package com.onionarch.wallet.purchaseorder;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PurchaseOrderEntityJPA extends CrudRepository<PurchaseOrderEntity, UUID> {

}
