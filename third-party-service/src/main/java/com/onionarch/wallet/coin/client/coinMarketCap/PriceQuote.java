package com.onionarch.wallet.coin.client.coinMarketCap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record PriceQuote(@JsonProperty("price") Double price,
                         @JsonProperty("last_updated") ZonedDateTime lastUpdated) {

    public Double getPrice() {
        return Math.ceil(price);
    }
}
