package com.bnp.kata.DevelopmentBooks.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentReceiptDTO {
    private BigDecimal price = BigDecimal.ZERO;
}
