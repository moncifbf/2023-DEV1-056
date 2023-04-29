package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

class PricingServiceTest {
    public static final BigDecimal EXPECTED_ONE_BOOK_PRICE = BigDecimal.valueOf(50).setScale(1, RoundingMode.HALF_EVEN);

    private PricingService pricingService;

    @BeforeEach
    void setUp() {
        pricingService = new PricingService();
    }

    @Test
    void getPrice_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_ONE_BOOK_PRICE, paymentReceiptDTO.getPrice());
    }
}
