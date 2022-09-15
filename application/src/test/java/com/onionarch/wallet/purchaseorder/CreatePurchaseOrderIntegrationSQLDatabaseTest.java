package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.Coin;
import com.onionarch.wallet.coin.CoinIntegration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreatePurchaseOrderIntegrationSQLDatabaseTest {

    @Autowired
    private PurchaseOrderEntityJPA purchaseOrderEntityJPA;

    @Mock
    private CoinIntegration coinIntegration;

    private CreatePurchaseOrder createPurchaseOrder;
    private PurchaseOrderRepository purchaseOrderRepository;

    @BeforeEach
    void setUp() {
        var converter = new PurchaseOrderConverter();
        var purchaseOrderRepositoryEntity = new PurchaseOrderRepositorySpringData(purchaseOrderEntityJPA);

        purchaseOrderRepository = new PurchaseOrderRepositoryImpl(purchaseOrderRepositoryEntity, converter);
        createPurchaseOrder = CreatePurchaseOrder.getInstance(purchaseOrderRepository, coinIntegration);
    }
    @Test
    void shouldSaveAndReturnAPurchaseOrder() {
        var walletId = UUID.randomUUID();
        var purchaseOrder = new PurchaseOrder("BTC", 0.5D, walletId);
        when(coinIntegration.fetchCoinBy(eq("BTC"))).thenReturn(new Coin("BTC", 1100D));

        createPurchaseOrder.execute(purchaseOrder);

        var purchaseOrderSaved = purchaseOrderRepository.findAll().stream().findAny().get();

        Assertions.assertAll(
                ()-> Assertions.assertEquals(walletId, purchaseOrderSaved.walletId()),
                ()-> Assertions.assertEquals("BTC", purchaseOrderSaved.coin()),
                ()-> Assertions.assertEquals(0.5D, purchaseOrderSaved.quantity())
        );
    }
}