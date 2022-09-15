package com.onionarch.wallet.purchaseorder;

import com.onionarch.wallet.coin.Coin;
import com.onionarch.wallet.coin.CoinIntegration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseOrderTest {

    private CreatePurchaseOrder createPurchaseOrder;

    @Mock
    private PurchaseOrderRepository purchaseOrderRepository;

    @Mock
    private CoinIntegration coinIntegration;

    @BeforeEach
    void setUp() {
        createPurchaseOrder = CreatePurchaseOrder.getInstance(purchaseOrderRepository, coinIntegration);
    }

    @Test
    void shouldCreateAndReturnPurchaseOrder() {
        var purchaseOrderArgumentCaptor = ArgumentCaptor.forClass(PurchaseOrder.class);
        var walletId = UUID.randomUUID();
        var purchaseOrder = new PurchaseOrder("BTC", 0.5D, walletId);
        createPurchaseOrder.execute(purchaseOrder);

        verify(purchaseOrderRepository).save(purchaseOrderArgumentCaptor.capture());

        var purchaseOrderSaved = purchaseOrderArgumentCaptor.getValue();

        Assertions.assertAll(
                ()-> Assertions.assertEquals(walletId, purchaseOrderSaved.walletId()),
                ()-> Assertions.assertEquals("BTC", purchaseOrderSaved.coin()),
                ()-> Assertions.assertEquals(0.5D, purchaseOrderSaved.quantity())
        );
    }

    @Test
    void shouldGetCurrentCoinValueAndSaveWhenCreateAPurchaseOrder() {
        var purchaseOrderArgumentCaptor = ArgumentCaptor.forClass(PurchaseOrder.class);
        var walletId = UUID.randomUUID();
        var purchaseDate = ZonedDateTime.of(
                LocalDate.of(2022, 2, 28),
                LocalTime.of(9, 0), ZoneId.of("UTC")
        );
        var purchaseOrder = new PurchaseOrder("BTC", 0.5D, walletId, purchaseDate);

        when(coinIntegration.fetchCoinBy(eq(purchaseOrder.coin()))).thenReturn(new Coin("BTC", 1100D));

        createPurchaseOrder.execute(purchaseOrder);

        verify(purchaseOrderRepository).save(purchaseOrderArgumentCaptor.capture());
        verify(coinIntegration).fetchCoinBy(eq(purchaseOrder.coin()));

        var purchaseOrderSaved = purchaseOrderArgumentCaptor.getValue();
        Assertions.assertAll(
                ()-> Assertions.assertEquals(walletId, purchaseOrderSaved.walletId()),
                ()-> Assertions.assertEquals("BTC", purchaseOrderSaved.coin()),
                ()-> Assertions.assertEquals(0.5D, purchaseOrderSaved.quantity()),
                ()-> Assertions.assertEquals(1100D, purchaseOrderSaved.purchaseValue()),
                ()-> Assertions.assertEquals(purchaseDate, purchaseOrderSaved.purchaseDate())
        );
    }
}