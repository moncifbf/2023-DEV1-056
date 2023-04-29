package com.bnp.kata.DevelopmentBooks.strategies;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscountResolver {

    public static BigDecimal getDiscountPrice(int size) {
        BigDecimal finalPrice = BigDecimal.ZERO;
        switch (size) {
            case 1:
                finalPrice = DiscountStrategy.oneBookStrategy().apply(size);
                break;
            case 2:
                finalPrice = DiscountStrategy.twoBooksStrategy().apply(size);
                break;
            case 3:
                finalPrice = DiscountStrategy.threeBooksStrategy().apply(size);
                break;
            case 4:
                finalPrice = DiscountStrategy.fourBooksStrategy().apply(size);
                break;
            case 5:
                finalPrice = DiscountStrategy.fiveBooksStrategy().apply(size);
                break;
            default:
                throw new IllegalArgumentException("Invalid discount strategy based on size: " + size);
        }
        return finalPrice;
    }
}
