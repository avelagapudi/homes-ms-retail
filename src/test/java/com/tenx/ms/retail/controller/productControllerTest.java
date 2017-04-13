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
import com.tenx.ms.retail.service.ProductService;
import com.tenx.ms.retail.service.StoreService;

import com.tenx.ms.retail.dto.ProductDTO;
import com.tenx.ms.retail.dto.StoreDTO;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class productControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private StoreService storeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void addProduct() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StoreDTO store = new StoreDTO(2L, "test2Store");
        ProductDTO product = new ProductDTO(2L,"testProduct","description about test product","3KY45",1234.56);

        when(storeService.getStoreById(2L)).thenReturn(store);
        doReturn(product).when(productService).addProduct(product);

        mockMvc.perform(post("/v1/products/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(product)))
                .andExpect(status().isCreated());

    }

    @Test
    public void addProduct_conflict() throws Exception{

    }

    @Test
    public void getProductsByStore() throws Exception{
        List<ProductDTO> products = Arrays.asList(
                new ProductDTO(3L,"testProduct","test product description","3KY45",1234.56),
                new ProductDTO(3L,"testSecondProduct", "test product description","45HYT",4567.78));

        when(productService.getProductsByStore(3L)).thenReturn(products);

        mockMvc.perform(get("/v1/products/{id}", 3L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void getProductByStoreIdProductId() throws Exception{
        ProductDTO product = new ProductDTO(3L,"testProduct","test product description","3KY45",1234.56);

        when(productService.getProductByStoreIdProductId(3L,2L)).thenReturn(product);

        mockMvc.perform(get("/v1/products/{storeId}/{productId}", 3L, 2L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(3))
                .andExpect(jsonPath("name").value("testProduct"))
                .andExpect(jsonPath("description").value("test product description"))
                .andExpect(jsonPath("sku").value("3KY45"))
                .andExpect(jsonPath("price").value(1234.56));

    }

    @Test
    public void getProductByStoreIdProductName() throws Exception{
        ProductDTO product = new ProductDTO(3L,"testProduct","test product description","3KY45",1234.56);

        when(productService.getProductByStoreIdProductName(3L,"testProduct")).thenReturn(product);

        mockMvc.perform(get("/v1/products/{storeId}/{productName}", 3L, "testProduct"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(3))
                .andExpect(jsonPath("name").value("testProduct"))
                .andExpect(jsonPath("description").value("test product description"))
                .andExpect(jsonPath("sku").value("3KY45"))
                .andExpect(jsonPath("price").value(1234.56));
    }

    @Test
    public void getProductsByStore_NotFound() throws Exception{
        when(productService.getProductsByStore(4L)).thenReturn(null);

        mockMvc.perform(get("/v1/products/{storeId}", 4L))
                .andExpect(status().isNoContent());

    }

    @Test
    public void getProductByStoreIdProductId_NotFound() throws Exception{
        when(productService.getProductByStoreIdProductId(4L,2L)).thenReturn(null);

        mockMvc.perform(get("/v1/products/{storeId}/{productId}", 4L, 2L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getProductByStoreIdProductName_NotFound() throws Exception {
        when(productService.getProductByStoreIdProductName(4L,"test")).thenReturn(null);

        mockMvc.perform(get("/v1/products/{storeId}/{productName}", 4L, "test"))
                .andExpect(status().isNotFound());
    }
}
