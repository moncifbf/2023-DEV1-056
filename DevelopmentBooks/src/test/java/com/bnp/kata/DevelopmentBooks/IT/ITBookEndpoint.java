package com.bnp.kata.DevelopmentBooks.IT;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ITBookEndpoint {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testGetPrice() {
        PurchaseDTO purchase = new PurchaseDTO();
        Map<String, Integer> bookQuantities = new HashMap<>();
        bookQuantities.put("Clean Code (Robert Martin, 2008)", 1);
        purchase.setBookQuantities(bookQuantities);

        PaymentReceiptDTO paymentReceiptDTO = new PaymentReceiptDTO();
        paymentReceiptDTO.setPrice(BigDecimal.valueOf(50));

        ResponseEntity<PaymentReceiptDTO> response = restTemplate.postForEntity("/api/v1/book/buy", purchase, PaymentReceiptDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
