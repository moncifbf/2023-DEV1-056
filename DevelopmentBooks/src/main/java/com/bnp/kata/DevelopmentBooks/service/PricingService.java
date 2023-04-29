package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PricingService {
    private static final BigDecimal BOOK_PRICE = new BigDecimal(50).setScale(1, RoundingMode.HALF_EVEN);


    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO result = new PaymentReceiptDTO();
        Map<String, Integer> bookQuantitiesMap = purchaseDTO.getBookQuantities();
        List<String> plainListBooks = getPlainList(bookQuantitiesMap);
        BigDecimal finalPrice = BOOK_PRICE.multiply(BigDecimal.valueOf(plainListBooks.size()));
        result.setPrice(finalPrice);
        return result;
    }

    public static List<String> getPlainList(Map<String, Integer> bookMap) {
        List<String> bookList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : bookMap.entrySet()) {
            String bookName = entry.getKey();
            int numBooks = entry.getValue();

            for (int i = 0; i < numBooks; i++) {
                bookList.add(bookName);
            }
        }

        return bookList;
    }
}
