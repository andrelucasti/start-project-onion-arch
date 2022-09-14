package com.onionarch.wallet.coin;

import org.springframework.stereotype.Service;

@Service
public class CoinIntegrationImpl implements CoinIntegration {

    private final CoinClient coinClient;

    public CoinIntegrationImpl(CoinClient coinClient) {
        this.coinClient = coinClient;
    }

    @Override
    public Coin fetchCoinBy(String coinSymbol) {
        return coinClient.fetchBy(coinSymbol);
    }
}
