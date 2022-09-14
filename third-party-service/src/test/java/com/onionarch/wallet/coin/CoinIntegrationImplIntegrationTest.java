package com.onionarch.wallet.coin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onionarch.wallet.coin.client.coinMarketCap.CoinMarketCapClient;
import com.onionarch.wallet.coin.client.coinMarketCap.CoinMarketCapClientAdapter;
import com.onionarch.wallet.coin.client.coinMarketCap.CoinMarketCapResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CoinIntegrationImplIntegrationTest {
    private static final String CONTRACT = "{\n" +
            "    \"status\": {\n" +
            "        \"timestamp\": \"2022-09-14T22:14:48.048Z\",\n" +
            "        \"error_code\": 0,\n" +
            "        \"error_message\": null,\n" +
            "        \"elapsed\": 1,\n" +
            "        \"credit_count\": 1,\n" +
            "        \"notice\": null\n" +
            "    },\n" +
            "    \"data\": {\n" +
            "        \"BTC\": {\n" +
            "            \"id\": 6486,\n" +
            "            \"name\": \"0hsta6hh7sad\",\n" +
            "            \"symbol\": \"BTC\",\n" +
            "            \"slug\": \"dbhex0yt287\",\n" +
            "            \"is_active\": 9272,\n" +
            "            \"is_fiat\": null,\n" +
            "            \"circulating_supply\": 4073,\n" +
            "            \"total_supply\": 369,\n" +
            "            \"max_supply\": 6564,\n" +
            "            \"date_added\": \"2022-09-14T22:14:48.048Z\",\n" +
            "            \"num_market_pairs\": 4439,\n" +
            "            \"cmc_rank\": 3711,\n" +
            "            \"last_updated\": \"2022-09-14T22:14:48.048Z\",\n" +
            "            \"tags\": [\n" +
            "                \"r2likzqwjrk\",\n" +
            "                \"btibjckg8fp\",\n" +
            "                \"jgtw8x8yn7s\",\n" +
            "                \"k7yf4yrm67i\",\n" +
            "                \"mihtgb1jhvc\",\n" +
            "                \"ys910pj8zpk\",\n" +
            "                \"hvk7xzbobvd\",\n" +
            "                \"qz50l2mh1sh\",\n" +
            "                \"o2sahmw159\",\n" +
            "                \"8pgbeylajio\"\n" +
            "            ],\n" +
            "            \"platform\": null,\n" +
            "            \"self_reported_circulating_supply\": null,\n" +
            "            \"self_reported_market_cap\": null,\n" +
            "            \"quote\": {\n" +
            "                \"USD\": {\n" +
            "                    \"price\": 19900.00,\n" +
            "                    \"volume_24h\": 0.9824566741253056,\n" +
            "                    \"volume_change_24h\": 0.7542947635498436,\n" +
            "                    \"percent_change_1h\": 0.1371399282956438,\n" +
            "                    \"percent_change_24h\": 0.8723955440069446,\n" +
            "                    \"percent_change_7d\": 0.1199333532663871,\n" +
            "                    \"percent_change_30d\": 0.326124497969704,\n" +
            "                    \"market_cap\": 0.618610957438205,\n" +
            "                    \"market_cap_dominance\": 498,\n" +
            "                    \"fully_diluted_market_cap\": 0.8649320972863406,\n" +
            "                    \"last_updated\": \"2022-09-14T22:14:48.048Z\"\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}";

    @Test
    void shouldFetchCoinByCoinSymbolWhenCoinMarketCapIsClient() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        var coinMarketCapResponse = objectMapper.readValue(CONTRACT, CoinMarketCapResponse.class);

        var coinMarketCapClient = mock(CoinMarketCapClient.class);

        when(coinMarketCapClient.fetchBy(anyString(), eq("BTC"))).thenReturn(coinMarketCapResponse);

        var coinIntegration = new CoinIntegrationImpl(new CoinMarketCapClientAdapter("token", coinMarketCapClient));
        var bitcoin = coinIntegration.fetchCoinBy("BTC");

        Assertions.assertAll(
                ()-> Assertions.assertEquals("BTC", bitcoin.coinSymbol()),
                ()-> Assertions.assertEquals(19900.00, bitcoin.value())
        );
    }

}