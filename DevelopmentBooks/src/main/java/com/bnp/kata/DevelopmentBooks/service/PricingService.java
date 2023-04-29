package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import com.bnp.kata.DevelopmentBooks.strategies.DiscountResolver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A service to calculate the price depending on the given inputs and the possible discounts
 * <p>
 * see {@code DiscountResolver}
 */
@Service
public class PricingService {

    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO result = new PaymentReceiptDTO();
        BigDecimal finalPrice = BigDecimal.valueOf(Double.MAX_VALUE); // We start by a maximum to change it to lower value depending on the calculation done later

        Map<String, Integer> bookQuantitiesMap = sortDescByValues(purchaseDTO.getBookQuantities()); // We sort the books by quantities DESC to start splitting after by the duplicated books first
        int size = bookQuantitiesMap.size();
        List<String> plainListBooks = getPlainList(bookQuantitiesMap);

        /*
         * We loop through each possible size of sets depending on the maximum possible size
         * The maximum size will be the number of distinct books, on our side with the given requirements it's 5
         *
         * Example: 2xBook1, 2xBook2, 1xBook3
         * First iteration: [[Book1], [Book1], [Book2], [Book2], [Book3]]
         * Second iteration: [[Book1, Book2], [Book1, Book2], [Book3]]
         * Last iteration: [[Book1, Book2, Book3], [Book1, Book2]]
         *
         * Depending on the result we calculate the best discount possible by calculating the price of each set of books.
         * */
        for (int maxListSize = 1; maxListSize <= size; maxListSize++) {
            List<List<String>> outputLists = splitToDistinctLists(plainListBooks, maxListSize);
            BigDecimal tempPrice = getDiscountedPrice(outputLists);
            if (tempPrice.compareTo(finalPrice) < 0) {
                finalPrice = tempPrice;  // We update the final price if the current price is lower
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
            boolean elementAdded = false; // flag to check if element is added to a sublist

            for (List<String> outputList : outputLists) {
                // if the element is not found in sublist and sublist is still less than the maximum size of list wanted
                if (!outputList.contains(element) && outputList.size() < maxListSize) {
                    outputList.add(element); // add the element to the sublist
                    elementAdded = true;
                    break;
                }
            }

            if (!elementAdded) { // if the element wasn't added to a sublist
                List<String> newOutputList = new ArrayList<>(); // create a new sublist
                newOutputList.add(element);
                outputLists.add(newOutputList);
            }
        }

        return outputLists;
    }

    private static List<String> getPlainList(Map<String, Integer> bookMap) {
        List<String> bookList = new ArrayList<>();
        bookMap.forEach((book, bookQuantity) -> bookList.addAll(Collections.nCopies(bookQuantity, book))); // nCopies used to create an inline collection by book quantity to add it into final booklist
        return bookList;
    }

    private static LinkedHashMap<String, Integer> sortDescByValues(Map<String, Integer> bookQuantities) {
        return bookQuantities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Sort the stream by values in descending order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new)); // Collect the sorted entries into a LinkedHashMap to keep the sorted order
    }
}
