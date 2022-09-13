package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.Coin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseOrderIntegrationTest {

    private CreatePurchaseOrder createPurchaseOrder;
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private CoinIntegration coinIntegration;


    @BeforeEach
    void setUp() {
        var converter = new PurchaseOrderConverter();
        purchaseOrderRepository = new PurchaseOrderRepositoryInMemory(converter);
        createPurchaseOrder = new CreatePurchaseOrder(purchaseOrderRepository, coinIntegration);
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