package com.onionarch.wallet.coin;

public interface CoinClient {
    Coin fetchBy(String coinSymbol);
}
