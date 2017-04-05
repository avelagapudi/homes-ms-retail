package com.tenx.ms.retail.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import com.tenx.ms.retail.controller.sampleController;


@RunWith(SpringJUnit4ClassRunner.class)
public class sampleControllerTest {

    //@Autowired
    //private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        //this.mockMvc = webAppContextSetup(ctx).build();
        this.mockMvc = standaloneSetup(new sampleController()).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void helloWorld() throws Exception {
        this.mockMvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                //.andExpect(content().contentType("text/plain"))
                .andExpect(content().string("Hello World Test!"));
    }

}
