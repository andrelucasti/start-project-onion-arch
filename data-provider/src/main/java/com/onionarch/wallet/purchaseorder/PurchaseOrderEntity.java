package com.onionarch.wallet.purchaseorder;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrderEntity {
    @Id
    @Column(name = "uuid", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "WALLET_ID", nullable = false)
    private UUID walletId;
    @Column(name = "COIN", nullable = false)
    private String coin;
    @Column(name = "QUANTITY", nullable = false)
    private Double quantity;
    @Column(name = "PURCHASE_VALUE", nullable = false)
    private Double purchaseValue;
    @Column(name = "PURCHASE_DATE", nullable = false)
    private ZonedDateTime purchaseDate;
    public PurchaseOrderEntity() {
    }
    public PurchaseOrderEntity(UUID walletId,
                               String coin,
                               Double quantity,
                               Double purchaseValue,
                               ZonedDateTime purchaseDate) {

        this.walletId = walletId;
        this.coin = coin;
        this.quantity = quantity;
        this.purchaseValue = purchaseValue;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public String getCoin() {
        return coin;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public ZonedDateTime getPurchaseDate() {
        return purchaseDate;
    }
}
