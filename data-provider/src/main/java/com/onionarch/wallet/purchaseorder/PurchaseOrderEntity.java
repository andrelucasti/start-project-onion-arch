package com.onionarch.wallet.purchaseorder;

import java.time.ZonedDateTime;
import java.util.UUID;

public record PurchaseOrderEntity(UUID walletId, String coin, double quantity, double purchaseValue, ZonedDateTime purchaseDate) {
}
