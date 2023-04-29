package com.bnp.kata.DevelopmentBooks.strategies;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DiscountStrategy {

    BigDecimal BOOK_PRICE = BigDecimal.valueOf(50);
    BigDecimal ONE_BOOK_DISCOUNT = BigDecimal.valueOf(1).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal TWO_BOOKS_DISCOUNT = BigDecimal.valueOf(0.95).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal THREE_BOOKS_DISCOUNT = BigDecimal.valueOf(0.9).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal FOUR_BOOKS_DISCOUNT = BigDecimal.valueOf(0.8).setScale(2, RoundingMode.HALF_EVEN);
    BigDecimal FIVE_BOOKS_DISCOUNT = BigDecimal.valueOf(0.75).setScale(2, RoundingMode.HALF_EVEN);

    BigDecimal apply(int size);

    static DiscountStrategy oneBookStrategy() {
        return size -> BOOK_PRICE.multiply(BigDecimal.valueOf(size)).multiply(ONE_BOOK_DISCOUNT);
    }

    static DiscountStrategy twoBooksStrategy() {
        return size -> BOOK_PRICE.multiply(BigDecimal.valueOf(size)).multiply(TWO_BOOKS_DISCOUNT);
    }

    static DiscountStrategy threeBooksStrategy() {
        return size -> BOOK_PRICE.multiply(BigDecimal.valueOf(size)).multiply(THREE_BOOKS_DISCOUNT);
    }

    static DiscountStrategy fourBooksStrategy() {
        return size -> BOOK_PRICE.multiply(BigDecimal.valueOf(size)).multiply(FOUR_BOOKS_DISCOUNT);
    }

    static DiscountStrategy fiveBooksStrategy() {
        return size -> BOOK_PRICE.multiply(BigDecimal.valueOf(size)).multiply(FIVE_BOOKS_DISCOUNT);
    }
}
