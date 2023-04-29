package com.bnp.kata.DevelopmentBooks.endpoint;

import com.bnp.kata.DevelopmentBooks.model.PaymentReceiptDTO;
import com.bnp.kata.DevelopmentBooks.service.PricingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookEndpoint.class)
class BookEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingService pricingService;

    @Test
    void buyBooks_ShouldReturnPaymentReceipt_For_One_Book() throws Exception {
        PaymentReceiptDTO paymentReceiptDTO = new PaymentReceiptDTO();
        paymentReceiptDTO.setPrice(new BigDecimal("50"));

        when(pricingService.getPrice(any())).thenReturn((paymentReceiptDTO));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book/buy")
                        .contentType(APPLICATION_JSON)
                        .content("{\"Clean Code (Robert Martin, 2008)\":1}")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("price").value("50"));
    }
}
