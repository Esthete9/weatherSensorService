package com.example.ProjectThreeRestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement", schema = "public")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "поле имя сенсора, которому принадлежит измерение не может быть пустым")
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @NotNull(message = "поле value не может быть пустым")
    @Min(value = -100, message = "значение температуры не может быть меньше -100")
    @Max(value = 100, message = "значение температуры не может быть больше 100")
    @Column(name = "value")
    private double value;

    @NotNull(message = "поле raining не может быть пустым")
    @Column(name = "raining")
    private boolean raining;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    public Measurement() {}

    public Measurement(boolean raining, LocalDateTime createdAt) {
        this.raining = raining;
        this.createdAt = createdAt;
    }

    public Measurement(int id, Sensor sensor, double value, boolean raining, LocalDateTime createdAt) {
        this.id = id;
        this.sensor = sensor;
        this.value = value;
        this.raining = raining;
        this.createdAt = createdAt;
    }

    public Measurement(Sensor sensor, double value, boolean raining) {
        this.sensor = sensor;
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", sensor=" + sensor +
                ", value=" + value +
                ", raining=" + raining +
                ", createdAt=" + createdAt +
                '}';
    }
}
