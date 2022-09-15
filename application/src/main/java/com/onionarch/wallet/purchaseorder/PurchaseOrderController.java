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
    private final PurchaseOrderControllerConverter purchaseOrderControllerConverter;


    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository,
                                   CoinIntegration coinIntegration,
                                   PurchaseOrderControllerConverter purchaseOrderControllerConverter) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.coinIntegration = coinIntegration;
        this.purchaseOrderControllerConverter = purchaseOrderControllerConverter;
    }


    @PostMapping
    public ResponseEntity<PurchaseOrderResponse> createPurchaseOrder(@RequestBody PurchaseOrderRequest purchaseOrderRequest){
        var purchaseOrder = purchaseOrderControllerConverter.convert(purchaseOrderRequest);
        var purchaseOrderReturn  = CreatePurchaseOrder
                .getInstance(purchaseOrderRepository, coinIntegration)
                .execute(purchaseOrder);

        var response = purchaseOrderControllerConverter.convert(purchaseOrderReturn);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
