package com.example.ProjectThreeRestApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 30, message = "Имя сенсора должно содеражать не менее 3 и не более 30 символов")
    @NotNull(message = "Имя сенсора не может быть пустым")
    private String name;

    public SensorDTO() {}

    public SensorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
