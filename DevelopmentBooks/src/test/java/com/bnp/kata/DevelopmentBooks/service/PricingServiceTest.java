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
    public static final BigDecimal EXPECTED_TWO_BOOKS_PRICE = BigDecimal.valueOf(100).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_THREE_BOOKS_PRICE = BigDecimal.valueOf(150).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_FOUR_BOOKS_PRICE = BigDecimal.valueOf(200).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_FIVE_BOOKS_PRICE = BigDecimal.valueOf(250).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_TWO_DIFFERENT_BOOKS_PRICE = new BigDecimal("95").setScale(1, RoundingMode.HALF_EVEN);

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

    @Test
    void getPrice_2() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 2);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_TWO_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_3() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 3);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_THREE_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_4() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 4);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_FOUR_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_5() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 5);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_FIVE_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_1_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 1);
        booksQuantities.put("Book2", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        Assertions.assertEquals(EXPECTED_TWO_DIFFERENT_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }
}
