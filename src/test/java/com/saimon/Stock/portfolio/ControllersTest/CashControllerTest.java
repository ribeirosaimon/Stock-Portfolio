package com.saimon.Stock.portfolio.ControllersTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class CashControllerTest {
    private final String DEPOSIT_URI = "/deposit";
    private final String WITHDRAW_URI = "/withdraw";
    private final String BALANCE_URI = "/balance";

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("TESTE")
    public void teste(){
        assert 1==1;
    }
    @Test
    @DisplayName("Is Forbidden Status")
    public void notAuthentication() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/deposit"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

//    @Test
//    @DisplayName("Deposit value in account")
//    public void depositValue() throws Exception {
//        String json = new ObjectMapper().writeValueAsString(null);
//        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
//                .post(DEPOSIT_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(json);
//
//        mockMvc
//                .perform(request)
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("value").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("national").isNotEmpty())
//                .andExpect(MockMvcResultMatchers.jsonPath("timestamp").isNotEmpty())
//                ;
//
//    }
}
