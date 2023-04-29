package com.bnp.kata.DevelopmentBooks.service;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.model.PurchaseDTO;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public PaymentReceiptDTO getPrice(PurchaseDTO purchaseDTO) {
        PaymentReceiptDTO result = new PaymentReceiptDTO();

        return result;
    }
}
