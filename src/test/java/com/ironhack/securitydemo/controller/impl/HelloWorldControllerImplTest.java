package com.ironhack.securitydemo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.securitydemo.controller.dto.JustNameDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc // new annotation
class HelloWorldControllerImplTest {

//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Hacer bodies

    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void helloWorld() throws Exception {
        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/hello-world"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello World :D"));
    }

    @Test
    void helloName() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/hello/Blanca"))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Blanca"));
    }

    @Test
    void helloPost() throws Exception {
        JustNameDTO justNameDTO = new JustNameDTO();
        justNameDTO.setName("Lucia");
        String body = objectMapper.writeValueAsString(justNameDTO);
        MvcResult mvcResult = mockMvc.perform(
                post("/hello-post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lucia"));

    }
}