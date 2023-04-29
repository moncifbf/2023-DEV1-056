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
        BigDecimal totalPrice = BigDecimal.ZERO;

        PaymentReceiptDTO result = new PaymentReceiptDTO();
        Map<String, Integer> bookQuantitiesMap = sortDescByValues(purchaseDTO.getBookQuantities());
        List<String> plainListBooks = getPlainList(bookQuantitiesMap);

        List<List<String>> outputLists = splitToDistinctLists(plainListBooks);

        totalPrice = getDiscountedPrice(totalPrice, outputLists);


        BigDecimal finalPrice = totalPrice.setScale(1, RoundingMode.HALF_EVEN);
        result.setPrice(finalPrice);
        return result;
    }

    private static BigDecimal getDiscountedPrice(BigDecimal totalPrice, List<List<String>> outputLists) {
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
        return totalPrice;
    }

    private static List<List<String>> splitToDistinctLists(List<String> plainListBooks) {
        List<List<String>> outputLists = new ArrayList<>();

        for (String element : plainListBooks) {
            boolean elementAdded = false;

            for (List<String> outputList : outputLists) {
                if (!outputList.contains(element) && outputList.size() < 4) {
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

    private LinkedHashMap<String, Integer> sortDescByValues(Map<String, Integer> bookQuantities) {
        return bookQuantities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
