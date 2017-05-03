package com.tenx.ms.retail.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenx.ms.retail.dto.ProductDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class productControllerIntegrationTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void addProduct() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductDTO product = new ProductDTO(1L,"testProduct","description about test product","3KY45",1234.56);

        mockMvc.perform(post("/v1/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(product)))
                .andExpect(status().isCreated());

    }

    @Test
    public void addProduct_conflict() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductDTO product = new ProductDTO(1L,"test product one","description of product one","LK1234",1234.56);

        mockMvc.perform(post("/v1/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(product)))
                .andExpect(status().isConflict());

    }

    @Test
    public void addProduct_InvalidRequest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        ProductDTO product = new ProductDTO(1L,"","description about test product","3KY45",1234.56);

        mockMvc.perform(post("/v1/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(product)))
                .andExpect(status().isPreconditionFailed());

    }

    @Test
    public void getProductsByStore() throws Exception{
        mockMvc.perform(get("/v1/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void getProductByStoreIdProductId() throws Exception{

        mockMvc.perform(get("/v1/products/{storeId}/{productId}", 1L, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(1))
                .andExpect(jsonPath("name").value("test product one"))
                .andExpect(jsonPath("description").value("description of product one"))
                .andExpect(jsonPath("sku").value("LK1234"))
                .andExpect(jsonPath("price").value(2500.00));

    }

    @Test
    public void getProductByStoreIdProductName() throws Exception{

        mockMvc.perform(get("/v1/products/{storeId}/{productName}", 1L, "test product one"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(1))
                .andExpect(jsonPath("name").value("test product one"))
                .andExpect(jsonPath("description").value("description of product one"))
                .andExpect(jsonPath("sku").value("LK1234"))
                .andExpect(jsonPath("price").value(2500.00));

    }


    @Test
    public void getProductsByStore_NotFound() throws Exception{
        mockMvc.perform(get("/v1/products/{storeId}", 5L))
                .andExpect(status().isNoContent());

    }

    @Test
    public void getProductByStoreIdProductId_NotFound() throws Exception{
        mockMvc.perform(get("/v1/products/{storeId}/{productId}", 4L, 2L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getProductByStoreIdProductName_NotFound() throws Exception {
        mockMvc.perform(get("/v1/products/{storeId}/{productName}", 4L, "test"))
                .andExpect(status().isNotFound());
    }


}
