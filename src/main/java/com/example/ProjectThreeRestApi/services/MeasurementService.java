package com.example.ProjectThreeRestApi.services;

import com.example.ProjectThreeRestApi.models.Measurement;

import java.util.List;

public interface MeasurementService {
    public List<Measurement> findAllMeasurement();
    public int findRainyDaysCount();
    public void save(Measurement measurement);
    public void enrichMeasurement(Measurement measurement);
}
