package com.example.ProjectThreeRestApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensor", schema = "public")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Имя сенсора должно содеражать не менее 3 и не более 30 символов")
    @NotNull(message = "Имя сенсора не может быть пустым")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurementList;

    public Sensor() {}

    public Sensor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
