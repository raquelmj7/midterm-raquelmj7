package com.ironhack.securitydemo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.securitydemo.controller.dto.JustNameDTO;
import com.ironhack.securitydemo.model.Role;
import com.ironhack.securitydemo.model.User;
import com.ironhack.securitydemo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User technician, admin;
    private Role adminRole, techRole;

    private final ObjectMapper objectMapper = new ObjectMapper(); // Hacer bodies

    @BeforeEach
    void setUp() {
        technician = new User("technician", passwordEncoder.encode("123456"));
        techRole = new Role("TECHNICIAN", technician);
        technician.setRoles(Set.of(techRole));

        admin = new User("admin", passwordEncoder.encode("123456"));
        adminRole = new Role("ADMIN", admin);
        admin.setRoles(Set.of(adminRole));

        userRepository.saveAll(List.of(technician, admin));

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void helloWorld() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        // Validamos que el estatus de respuesta sea OK
        MvcResult mvcResult = mockMvc.perform(get("/hello-world").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hello World :D"));
    }

    @Test
    void helloName() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic YWRtaW46MTIzNDU2");
        MvcResult mvcResult = mockMvc.perform(get("/hello/Blanca").headers(httpHeaders))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Blanca"));
    }

    @Test
    void helloPost() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic dGVjaG5pY2lhbjoxMjM0NTY=");

        JustNameDTO justNameDTO = new JustNameDTO();
        justNameDTO.setName("Lucia");
        String body = objectMapper.writeValueAsString(justNameDTO);
        MvcResult mvcResult = mockMvc.perform(
                post("/hello-post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(httpHeaders)
                )
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Lucia"));

    }
}