package com.onionarch.wallet.coin;

public interface CoinIntegration {
    Coin fetchCoinBy(String coinSymbol);
}
