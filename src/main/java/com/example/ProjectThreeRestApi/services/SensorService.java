package com.example.ProjectThreeRestApi.services;

import com.example.ProjectThreeRestApi.models.Sensor;

public interface SensorService {
    public Sensor findOne(String name);
    public void save(Sensor sensor);
}
