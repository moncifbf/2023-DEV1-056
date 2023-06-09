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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PricingServiceTest {
    
    public static final BigDecimal EXPECTED_ONE_BOOK_PRICE = BigDecimal.valueOf(50).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_TWO_BOOKS_PRICE = BigDecimal.valueOf(100).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_THREE_BOOKS_PRICE = BigDecimal.valueOf(150).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_FOUR_BOOKS_PRICE = BigDecimal.valueOf(200).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_FIVE_BOOKS_PRICE = BigDecimal.valueOf(250).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_TWO_DIFFERENT_BOOKS_PRICE = new BigDecimal(95).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_THREE_DIFFERENT_BOOKS_PRICE = new BigDecimal(135).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_THREE_BOOKS_TWO_EACH_AND_TWO_DIFFERENT_BOOKS = new BigDecimal(320).setScale(1, RoundingMode.HALF_EVEN);
    public static final BigDecimal EXPECTED_FOUR_BOOKS_TWO_EACH_AND_ONE_DIFFERENT = new BigDecimal("347.5").setScale(1, RoundingMode.HALF_EVEN);

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
        assertEquals(EXPECTED_ONE_BOOK_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_2() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 2);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_TWO_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_3() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 3);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_THREE_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_4() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 4);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_FOUR_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_5() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 5);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_FIVE_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_1_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 1);
        booksQuantities.put("Book2", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_TWO_DIFFERENT_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_1_1_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 1);
        booksQuantities.put("Book2", 1);
        booksQuantities.put("Book3", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_THREE_DIFFERENT_BOOKS_PRICE, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_2_2_2_1_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 2);
        booksQuantities.put("Book2", 2);
        booksQuantities.put("Book3", 2);
        booksQuantities.put("Book4", 1);
        booksQuantities.put("Book5", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_THREE_BOOKS_TWO_EACH_AND_TWO_DIFFERENT_BOOKS, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_2_2_2_2_1() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 2);
        booksQuantities.put("Book2", 2);
        booksQuantities.put("Book3", 2);
        booksQuantities.put("Book4", 2);
        booksQuantities.put("Book5", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        PaymentReceiptDTO paymentReceiptDTO = pricingService.getPrice(purchaseDTO);
        assertEquals(EXPECTED_FOUR_BOOKS_TWO_EACH_AND_ONE_DIFFERENT, paymentReceiptDTO.getPrice());
    }

    @Test
    void getPrice_IllegalStrategy() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        Map<String, Integer> booksQuantities = new HashMap<>();
        booksQuantities.put("Book1", 2);
        booksQuantities.put("Book2", 2);
        booksQuantities.put("Book3", 2);
        booksQuantities.put("Book4", 2);
        booksQuantities.put("Book6", 1);
        booksQuantities.put("Book7", 1);
        booksQuantities.put("Book8", 1);
        booksQuantities.put("Book9", 1);
        booksQuantities.put("Book10", 1);
        purchaseDTO.setBookQuantities(booksQuantities);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            pricingService.getPrice(purchaseDTO);
        });

        assertTrue(thrown.getMessage().contains("Invalid discount strategy based on size"));
    }
}
