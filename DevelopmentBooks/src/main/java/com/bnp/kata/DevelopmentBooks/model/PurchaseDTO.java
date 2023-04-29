package com.bnp.kata.DevelopmentBooks.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PurchaseDTO {
    Map<String, Integer> bookQuantities = new HashMap<>();
}
