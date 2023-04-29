package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import com.bnp.kata.DevelopmentBooks.strategies.DiscountResolver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PricingService {

    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO result = new PaymentReceiptDTO();
        BigDecimal finalPrice = BigDecimal.valueOf(Double.MAX_VALUE);

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
            BigDecimal discount = DiscountResolver.getDiscountPrice(list.size());
            totalPrice = totalPrice.add(discount);
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
