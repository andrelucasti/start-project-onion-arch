package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.Coin;

public interface CoinIntegration {

    Coin fetchCoinBy(String coinSymbol);
}
