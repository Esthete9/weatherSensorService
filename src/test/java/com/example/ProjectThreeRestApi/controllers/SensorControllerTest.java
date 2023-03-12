package com.example.ProjectThreeRestApi.controllers;

import com.example.ProjectThreeRestApi.services.SensorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
public class SensorControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void registrationSensor_shouldReturnOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"Platon Sensor\"}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void registrationSensor_shouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/sensors/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\" : \"Ignat Sensor\"}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("name - Такое имя сенсора уже существует!;"));;
    }
}
