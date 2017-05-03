package com.tenx.ms.retail.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenx.ms.retail.dto.StoreDTO;
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
public class storeControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addStore() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StoreDTO store = new StoreDTO("test store 5");

        mockMvc.perform(post("/v1/stores")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(store)))
                .andExpect(status().isOk());
    }

    @Test
    public void addStore_InvalidRequest() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StoreDTO store = new StoreDTO("");

        mockMvc.perform(post("/v1/stores")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(store)))
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    public void getAllStores() throws Exception{
        mockMvc.perform(get("/v1/stores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void getStoreById() throws Exception{
        mockMvc.perform(get("/v1/stores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(1))
                .andExpect(jsonPath("store_name").value("test store one"));
    }

    @Test
    public void getStoreByName() throws Exception{
        mockMvc.perform(get("/v1/stores/{name}", "test store one"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(1))
                .andExpect(jsonPath("store_name").value("test store one"));
    }

    @Test
    public void getStoreById_NotFound() throws Exception{
        mockMvc.perform(get("/v1/stores/{id}", 10L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getStoreByName_NotFound() throws Exception{
        mockMvc.perform(get("/v1/stores/{name}", "test"))
                .andExpect(status().isNotFound());
    }


}
