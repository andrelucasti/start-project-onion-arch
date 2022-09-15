package com.onionarch.wallet.coin.client.coinMarketCap;

import com.onionarch.wallet.coin.Coin;
import com.onionarch.wallet.coin.CoinClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoinMarketCapClientAdapter implements CoinClient {

    private final String token;
    private final CoinMarketCapClient coinMarketCapClient;


    public CoinMarketCapClientAdapter(@Value("${feign.client.coinMarketCap.token}") String token,
                                      CoinMarketCapClient coinMarketCapClient) {
        this.token = token;
        this.coinMarketCapClient = coinMarketCapClient;
    }

    @Override
    public Coin fetchBy(String coinSymbol) {
        var coinMarketCapResponse = coinMarketCapClient.fetchBy(token, coinSymbol);

        var coinData = coinMarketCapResponse.coinData().get(coinSymbol);
        var priceQuote = coinData.quote().get(QuoteCurrency.USD.name());

        return new Coin(coinData.symbol(), priceQuote.getPrice());
    }
}
