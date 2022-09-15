package com.onionarch.wallet.purchaseorder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record PurchaseOrderRequest(@JsonProperty(value = "walletId", required = true) UUID walletId,
                                   @JsonProperty(value = "coinSymbol", required = true) String coinSymbol,
                                   @JsonProperty(value = "coinAmount", required = true) double coinAmount) {
}
