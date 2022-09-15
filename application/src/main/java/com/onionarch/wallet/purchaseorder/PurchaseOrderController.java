package com.onionarch.wallet.purchaseorder;


import com.onionarch.wallet.coin.CoinIntegration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase-order")
public class PurchaseOrderController {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final CoinIntegration coinIntegration;

    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository,
                                   CoinIntegration coinIntegration) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.coinIntegration = coinIntegration;
    }


    @PostMapping
    public ResponseEntity<PurchaseOrderResponse> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest){
        var purchaseOrderReturn  = CreatePurchaseOrder.getInstance(purchaseOrderRepository, coinIntegration)
                .execute(new PurchaseOrder(
                        purchaseOrderRequest.coinSymbol(),
                        purchaseOrderRequest.coinAmount(),
                        purchaseOrderRequest.walletId())
                );


        var response = new PurchaseOrderResponse(
                purchaseOrderReturn.walletId(),
                purchaseOrderReturn.coin(),
                purchaseOrderReturn.quantity(),
                purchaseOrderReturn.purchaseValue(),
                purchaseOrderReturn.purchaseDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
