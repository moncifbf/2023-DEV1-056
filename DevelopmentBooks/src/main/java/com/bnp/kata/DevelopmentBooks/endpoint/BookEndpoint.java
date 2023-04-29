package com.bnp.kata.DevelopmentBooks.endpoint;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/book")
public class BookEndpoint {

    @PostMapping("/buy")
    public ResponseEntity<PaymentReceiptDTO> buy(@RequestBody PurchaseDTO purchaseDTO) {
        return null;
    }
}
