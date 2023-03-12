package com.example.ProjectThreeRestApi.util;

import com.example.ProjectThreeRestApi.models.Sensor;
import com.example.ProjectThreeRestApi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorRepository.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Такое имя сенсора уже существует!");
        }
    }

    public void validateSensor(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (!sensorRepository.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("sensor", "", "Сенсора с таким именем не существует!");
        }
    }
}
