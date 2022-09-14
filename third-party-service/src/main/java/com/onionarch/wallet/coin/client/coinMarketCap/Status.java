package com.onionarch.wallet.coin.client.coinMarketCap;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record Status(@JsonProperty(value = "timestamp") ZonedDateTime timestamp,
                     @JsonProperty(value = "error_code") Integer errorCode,
                     @JsonProperty(value = "error_message") String errorMessage,
                     @JsonProperty(value = "elapsed") Integer elapsed,
                     @JsonProperty(value = "credit_count") Integer creditCount,
                     @JsonProperty(value = "notice") String notice) {
}
