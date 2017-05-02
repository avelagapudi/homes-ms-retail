package com.tenx.ms.retail.controller;

import com.tenx.ms.retail.dto.OrderDTO;
import com.tenx.ms.retail.dto.ProductOrderDTO;
import com.tenx.ms.retail.service.OrderService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class orderControllerExceptionTest {
    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

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
        order.setStatus(1);

        when(orderService.createOrder(order)).thenThrow(new NoSuchElementException("Not Found"));

        mockMvc.perform(post("/v1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void createOrder_InvalidProductId() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductOrderDTO product = new ProductOrderDTO();
        product.setProductId(3L);
        product.setCount(5);

        Set<ProductOrderDTO> products = new HashSet();
        products.add(product);

        OrderDTO order = new OrderDTO();
        order.setProducts(products);
        order.setFirstName("testFirstName");
        order.setLastName("testLastName");
        order.setEmail("test@ten-x.com");
        order.setPhone("1234567890");
        order.setStatus(1);


        when(orderService.createOrder(order)).thenThrow(new NoSuchElementException("Stock Not Found"));

        mockMvc.perform(post("/v1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isNotFound());
    }

}


