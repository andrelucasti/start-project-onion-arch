package com.onionarch.wallet.purchaseorder;

import java.time.ZonedDateTime;
import java.util.UUID;

public record PurchaseOrder(String coin, double quantity, UUID walletId, double purchaseValue, ZonedDateTime purchaseDate) {
    public PurchaseOrder(String coin, double quantity, UUID walletId) {
        this(coin, quantity, walletId, 0D, ZonedDateTime.now());
    }

    public PurchaseOrder(String coin, double quantity, UUID walletId, ZonedDateTime purchaseDate) {
        this(coin, quantity, walletId, 0D, purchaseDate);
    }
}
