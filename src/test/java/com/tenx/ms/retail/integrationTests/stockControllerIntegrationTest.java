package com.tenx.ms.retail.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.tenx.ms.retail.dto.StockDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class stockControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addStock() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StockDTO stock = new StockDTO() ;
        stock.setCount(20);

        mockMvc.perform(post("/v1/stock/{storeId}/{productId}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateStock() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StockDTO stock = new StockDTO() ;
        stock.setCount(20);

        mockMvc.perform(post("/v1/stock/{storeId}/{productId}", 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateStock_Notfound() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StockDTO stock = new StockDTO() ;
        stock.setCount(20);

        mockMvc.perform(post("/v1/stock/{storeId}/{productId}", 1L, 10L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void updateStock_InvalidRequest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StockDTO stock = new StockDTO() ;

        mockMvc.perform(post("/v1/stock/{storeId}/{productId}", 1L, 3L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isPreconditionFailed());

    }


}
