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
import com.tenx.ms.retail.service.StoreService;

import com.tenx.ms.retail.dto.StoreDTO;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class storeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StoreController storeController;

    @Mock
    private StoreService storeService;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8")
    );

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    public void addStore() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        StoreDTO store = new StoreDTO("test8Store");

        doReturn(store).when(storeService).addStore(store);
        mockMvc.perform(post("/v1/stores")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(store)))
                .andExpect(status().isCreated());
    }

    @Test
    public void getAllStores() throws Exception{
        List<StoreDTO> stores = Arrays.asList(
                new StoreDTO(1L, "test1Store"),
                new StoreDTO (2L, "test2Store")
        );

        when(storeService.getAllStores()).thenReturn(stores);
        mockMvc.perform(get("/v1/stores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

    }

    @Test
    public void getStoreById() throws Exception{
        StoreDTO store = new StoreDTO(2L,"testStore");

        when(storeService.getStoreById(2L)).thenReturn(store);

        mockMvc.perform(get("/v1/stores/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(2))
                .andExpect(jsonPath("store_name").value("testStore"));
    }

    @Test
    public void getStoreName() throws Exception{
        StoreDTO store = new StoreDTO(2L,"testStore");

        when(storeService.getStoreByName("testStore")).thenReturn(store);

        mockMvc.perform(get("/v1/stores/{name}", "testStore"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("store_id").value(2))
                .andExpect(jsonPath("store_name").value("testStore"));

    }

    @Test
    public void invalidAddStoreRequest() throws Exception{

        ObjectMapper mapper = new ObjectMapper();

        StoreDTO store = new StoreDTO("test8Store");
        when(storeService.getStoreByName(store.getStoreName())).thenReturn(store);
        mockMvc.perform(post("/v1/stores")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(store)))
                .andExpect(status().isConflict());
    }

    @Test
    public void getStoreById_NotFound() throws Exception{
        when(storeService.getStoreById(10L)).thenReturn(null);
        mockMvc.perform(get("/v1/stores/{id}", 10L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getStoreByName_NotFound() throws Exception{
        when(storeService.getStoreByName("test")).thenReturn(null);
        mockMvc.perform(get("/v1/stores/{name}", "test"))
                .andExpect(status().isNotFound());
    }

}
