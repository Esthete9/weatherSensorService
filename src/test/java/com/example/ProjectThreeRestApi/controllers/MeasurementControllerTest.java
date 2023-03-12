package com.example.ProjectThreeRestApi.controllers;

import com.example.ProjectThreeRestApi.models.Measurement;
import com.example.ProjectThreeRestApi.models.Sensor;
import com.example.ProjectThreeRestApi.services.MeasurementService;

import jakarta.servlet.ServletContext;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class MeasurementControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasurementService measurementService;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
        Assertions.assertNotNull(webApplicationContext.getBean("measurementController"));
    }

    @Test
    public void getAllMeasurement_shouldReturnAllMeasurement() throws Exception {
        when(measurementService.findAllMeasurement()).thenReturn(List.of(
                new Measurement(1, new Sensor(), 20.0, true,
                LocalDateTime.of(2023, 3, 4, 4, 43))));
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/measurement"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].value").value(20.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].raining").value("true"));
    }

    @Test
    public void getRainyDaysCount_shouldReturnCount() throws Exception {
        when(measurementService.findRainyDaysCount()).thenReturn(3);
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/measurement/rainyDaysCount"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("3"));
    }

    @Test
    public void create_shouldReturnOK() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/measurement/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\" : 55.0, \"raining\": false, \"sensor\": {\"name\" : \"Ignat Sensor\"}}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void create_shouldReturnError() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/measurement/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\" : 55.0, \"raining\": false, \"sensor\": {\"name\" : \"wrong name\"}}"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                .value("sensor - Сенсора с таким именем не существует!;"));
    }



}
