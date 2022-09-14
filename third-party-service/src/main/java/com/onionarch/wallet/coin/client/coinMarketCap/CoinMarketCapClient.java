package com.onionarch.wallet.coin.client.coinMarketCap;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "coinMarketCapClient",
        url = "${feign.client.coinMarketCap.url}")
public interface CoinMarketCapClient {
    @GetMapping(value = "v1/cryptocurrency/quotes/latest")
    CoinMarketCapResponse fetchBy(
            @RequestHeader(name = "X-CMC_PRO_API_KEY") String token,
            @RequestParam(name = "symbol") String coinSymbol);
}
