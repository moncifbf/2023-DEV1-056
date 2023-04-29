package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PricingService {
    private static final BigDecimal BOOK_PRICE = new BigDecimal(50).setScale(2, RoundingMode.HALF_EVEN);


    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO result = new PaymentReceiptDTO();
        BigDecimal finalPrice = BOOK_PRICE.multiply(BigDecimal.valueOf(purchaseDTO.getBookQuantities().size()));
        result.setPrice(finalPrice);
        return result;
    }
}
