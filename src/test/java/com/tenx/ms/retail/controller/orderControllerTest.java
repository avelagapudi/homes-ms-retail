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
import java.util.HashSet;
import java.util.Set;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
public class orderControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
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
        order.setStatus(1);
        order.setOrderDate(new Date());

        when(orderService.createOrder(order)).thenReturn(order);

        mockMvc.perform(post("/v1/orders/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(order)))
                .andExpect(status().isOk());

    }

}
