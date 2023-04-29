package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PricingService {
    private static final BigDecimal BOOK_PRICE = new BigDecimal(50).setScale(1, RoundingMode.HALF_EVEN);


    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        BigDecimal finalPrice = BigDecimal.valueOf(Double.MAX_VALUE);

        PaymentReceiptDTO result = new PaymentReceiptDTO();
        Map<String, Integer> bookQuantitiesMap = sortDescByValues(purchaseDTO.getBookQuantities());
        int size = bookQuantitiesMap.size();
        List<String> plainListBooks = getPlainList(bookQuantitiesMap);

        for (int maxListSize = 1; maxListSize <= size; maxListSize++) {
            List<List<String>> outputLists = splitToDistinctLists(plainListBooks, maxListSize);
            BigDecimal tempPrice = getDiscountedPrice(outputLists);
            if (tempPrice.compareTo(finalPrice) < 0) {
                finalPrice = tempPrice;
            }
        }
        result.setPrice(finalPrice);
        return result;
    }

    private static BigDecimal getDiscountedPrice(List<List<String>> outputLists) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (List<String> list : outputLists) {
            double discount = switch (list.size()) {
                case 2 -> 0.95;
                case 3 -> 0.90;
                case 4 -> 0.80;
                case 5 -> 0.75;
                default -> 1;
            };
            totalPrice = totalPrice.add(BOOK_PRICE.multiply(BigDecimal.valueOf(list.size())).multiply(BigDecimal.valueOf(discount)));
        }
        return totalPrice.setScale(1, RoundingMode.HALF_EVEN);
    }

    private static List<List<String>> splitToDistinctLists(List<String> plainListBooks, int maxListSize) {
        List<List<String>> outputLists = new ArrayList<>();

        for (String element : plainListBooks) {
            boolean elementAdded = false;

            for (List<String> outputList : outputLists) {
                if (!outputList.contains(element) && outputList.size() < maxListSize) {
                    outputList.add(element);
                    elementAdded = true;
                    break;
                }
            }

            if (!elementAdded) {
                List<String> newOutputList = new ArrayList<>();
                newOutputList.add(element);
                outputLists.add(newOutputList);
            }
        }
        return outputLists;
    }

    private static List<String> getPlainList(Map<String, Integer> bookMap) {
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

    private LinkedHashMap<String, Integer> sortDescByValues(Map<String, Integer> bookQuantities) {
        return bookQuantities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
