package com.bnp.kata.DevelopmentBooks.strategies;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountResolver {

    public static BigDecimal getDiscountPrice(int size) {
        return switch (size) {
            case 1 -> DiscountStrategy.oneBookStrategy().apply(size);
            case 2 -> DiscountStrategy.twoBooksStrategy().apply(size);
            case 3 -> DiscountStrategy.threeBooksStrategy().apply(size);
            case 4 -> DiscountStrategy.fourBooksStrategy().apply(size);
            case 5 -> DiscountStrategy.fiveBooksStrategy().apply(size);
            default -> throw new IllegalArgumentException("Invalid discount strategy based on size: " + size);
        };
    }
}
