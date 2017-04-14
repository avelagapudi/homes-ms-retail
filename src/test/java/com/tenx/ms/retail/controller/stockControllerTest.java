package com.tenx.ms.retail.controller;

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
import com.tenx.ms.retail.service.StockService;
import com.tenx.ms.retail.service.ProductService;
import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.dto.StockDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class stockControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private StockController stockController;

    @Mock
    private StockService stockService;

    @Mock
    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    public void updateStock() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductDTO product = new ProductDTO(1L, 2L, "testProduct", "test product description", "asdsads", 1234.56);
        StockDTO stock = new StockDTO(2L,1L,20) ;

        when(productService.getProductByStoreIdProductId(2L, 1L)).thenReturn(product);
        doReturn(stock).when(stockService).updateStock(stock);

        mockMvc.perform(post("/v1/stock/{storeId}/{productId}", 2L, 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(stock)))
                .andExpect(status().isCreated());

    }

    @Test
    public void updateStock_notFound() throws Exception{

    }


}
