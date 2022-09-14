package com.onionarch.wallet.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CoinIntegrationImplTest {

    @Test
    void shouldFetchCoinByCoinSymbol() {
        var coinClient = mock(CoinClient.class);
        when(coinClient.fetchBy(eq("BTC"))).thenReturn(new Coin("BTC", 19900.00));
        var coinIntegration = new CoinIntegrationImpl(coinClient);
        var bitcoin = coinIntegration.fetchCoinBy("BTC");

        Assertions.assertAll(
                ()-> Assertions.assertEquals("BTC", bitcoin.coinSymbol()),
                ()-> Assertions.assertEquals(19900.00, bitcoin.value())
        );
    }
}