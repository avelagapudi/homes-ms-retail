package com.tenx.ms.retail.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenx.ms.retail.dto.OrderDTO;
import com.tenx.ms.retail.dto.ProductOrderDTO;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class orderControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void createOrder() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(1L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setStoreId(1L);
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("2134567890");
        order.setStatus("ORDERED");
        order.setOrderDate(new Date());

        mockMvc.perform(post("/v1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isOk());

    }

    @Test
    public void createOrder_InvalidStoreId() throws Exception{

        ObjectMapper mapper = new ObjectMapper();

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(1L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("1234567890");
        order.setStatus("ORDERED");

        mockMvc.perform(post("/v1/orders/{id}", 5L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void createOrder_InvalidProductId() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(10L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("1234567890");
        order.setStatus("ORDERED");


        mockMvc.perform(post("/v1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isNotFound());
    }
}
