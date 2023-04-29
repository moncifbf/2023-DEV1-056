package com.bnp.kata.DevelopmentBooks.endpoint;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import com.bnp.kata.DevelopmentBooks.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/book")
public class BookEndpoint {

    private final PricingService pricingService;

    @PostMapping("/buy")
    public ResponseEntity<PaymentReceiptDTO> buy(@RequestBody PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        return new ResponseEntity<>(paymentReceiptDTO, HttpStatus.OK);
    }
}
