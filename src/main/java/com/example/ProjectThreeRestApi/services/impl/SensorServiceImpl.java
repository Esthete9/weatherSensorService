package com.example.ProjectThreeRestApi.services.impl;

import com.example.ProjectThreeRestApi.models.Sensor;
import com.example.ProjectThreeRestApi.repositories.SensorRepository;
import com.example.ProjectThreeRestApi.services.SensorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorServiceImpl(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor findOne(String name) {
        Optional<Sensor> foundSensor = sensorRepository.findByName(name);
        return foundSensor.orElse(null);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional
    public void update(String oldName, String newName) {
        Sensor sensor = findOne(oldName);
        sensor.setName(newName);
        sensorRepository.save(sensor);
    }

    @Transactional
    public void delete(Sensor sensorDelete) {
        sensorRepository.delete(findOne(sensorDelete.getName()));
    }


}
