package com.onionarch.wallet.purchaseorder;

import com.google.common.io.Resources;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PurchaseOrderControllerE2ETest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    void shouldReturnPurchaseOrderResponseWhenPurchaseOrderIsCreated() throws IOException {
        String walletId = UUID.randomUUID().toString();
        String coinSymbol = "BNB";
        double coinAmount = 2.0D;

        var purchaseOrderRequest = Resources.toString(Resources
                                .getResource("contracts/purchaseorder/request-purchase-order.json"),
                StandardCharsets.UTF_8)
                .replace("{walletId}", walletId)
                .replace("{coinSymbol}", coinSymbol)
                .replace("{coinAmount}", Double.toString(coinAmount));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(purchaseOrderRequest)
                .post("/purchase-order")
                .then()
                .status(HttpStatus.CREATED)
                .body("walletId", Matchers.equalTo(walletId))
                .body("coinSymbol", Matchers.equalTo(coinSymbol))
                .body("coinPrice", Matchers.not(Matchers.emptyOrNullString()))
                .body("purchaseTotalValue", Matchers.not(Matchers.emptyOrNullString()))
                .body("purchaseDate", Matchers.not(Matchers.emptyOrNullString()));
    }
}