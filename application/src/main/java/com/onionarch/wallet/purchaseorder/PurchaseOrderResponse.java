package com.onionarch.wallet.purchaseorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.UUID;

public record PurchaseOrderResponse(@JsonProperty("walletId") UUID walletId,
                                    @JsonProperty("coinSymbol") String coinSymbol,
                                    @JsonProperty("coinAmount") double coinAmount,
                                    @JsonProperty("coinPrice") double coinPrice,
                                    @JsonProperty("purchaseDate") ZonedDateTime purchaseDate) {

    @JsonProperty("purchaseTotalValue")
    public Double totalValue(){
        return coinPrice * coinAmount;
    }
}
