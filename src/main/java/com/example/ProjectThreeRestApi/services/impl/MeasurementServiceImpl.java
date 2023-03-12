package com.example.ProjectThreeRestApi.services.impl;

import com.example.ProjectThreeRestApi.models.Measurement;
import com.example.ProjectThreeRestApi.repositories.MeasurementRepository;
import com.example.ProjectThreeRestApi.services.MeasurementService;
import com.example.ProjectThreeRestApi.services.SensorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementServiceImpl(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAllMeasurement() {
        return measurementRepository.findAll();
    }

    public int findRainyDaysCount() {
        List<Measurement> measurementList = measurementRepository.findAll();

        Set<Integer> rainyDays = new HashSet<>();

        for (var measure : measurementList) {
            if (measure.isRaining()) {
                rainyDays.add(measure.getCreatedAt().getDayOfYear() + measure.getCreatedAt().getYear());
            }
        }

        return rainyDays.size();
    }

    @Transactional
    public void save(Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    public void enrichMeasurement(Measurement measurement) {
        measurement.setCreatedAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findOne(measurement.getSensor().getName()));
    }
}
