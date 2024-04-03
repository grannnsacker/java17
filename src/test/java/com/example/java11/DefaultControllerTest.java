package com.example.java11;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpClient;

//@WebMvcTest
//public class DefaultControllerTest {
//
//    @Autowired
//    MockMvc mock;
//
//    @Test
//    public void homeTest() throws Exception {
//        mock.perform(MockMvcRequestBuilders.get("/home"))
//                .andExpect(result -> Assertions.assertEquals(result.getResponse().getContentAsString(), "home"));
//    }
//}
