package com.example.ProjectThreeRestApi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull(message = "поле value не может быть пустым")
    @Min(value = -100, message = "значение температуры не может быть меньше -100")
    @Max(value = 100, message = "значение температуры не может быть больше 100")
    private double value;
    @NotNull(message = "поле raining не может быть пустым")
    private boolean raining;
    @NotNull(message = "поле не может быть пустым")
    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
