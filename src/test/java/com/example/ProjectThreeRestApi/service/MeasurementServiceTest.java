package com.example.ProjectThreeRestApi.service;

import com.example.ProjectThreeRestApi.models.Measurement;
import com.example.ProjectThreeRestApi.models.Sensor;
import com.example.ProjectThreeRestApi.repositories.MeasurementRepository;
import com.example.ProjectThreeRestApi.services.MeasurementService;
import com.example.ProjectThreeRestApi.services.SensorService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MeasurementServiceTest {

    @Autowired
    private MeasurementService measurementService;

    @MockBean
    private MeasurementRepository measurementRepository;

    @MockBean
    private SensorService sensorService;

    @Test
    public void findRainyDaysCount_shouldReturnRainyDaysCount() {
        List<Measurement> measurements = new ArrayList<>(
                List.of(new Measurement(1, new Sensor(), 20.0, true,
                                LocalDateTime.of(2023, 3, 4, 4, 43)),
                        new Measurement(1, new Sensor(), 20.0, false,
                                LocalDateTime.of(2023, 3, 2, 4, 43)),
                        new Measurement(1, new Sensor(), 20.0, true,
                                LocalDateTime.of(2023, 3, 4, 4, 43))));
        Mockito.doReturn(measurements).when(measurementRepository).findAll();
        int actual = 1;
        int expected = measurementService.findRainyDaysCount();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findRainyDaysCount_shouldNotReturnNull() {
        List<Measurement> measurements = new ArrayList<>();
        Mockito.doReturn(measurements).when(measurementRepository).findAll();
        int actual = 0;
        int expected = measurementService.findRainyDaysCount();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void enrichMeasurement_shouldReturnEnrichedMeasurement() {
        Mockito.doReturn(new Sensor()).when(sensorService).findOne("");
        Measurement measurement = new Measurement(1, new Sensor(), 20.0, false, null);
        measurementService.enrichMeasurement(measurement);

        Assertions.assertNotNull(measurement.getCreatedAt());
    }
}
