package com.example.ProjectThreeRestApi.controllers;

import com.example.ProjectThreeRestApi.dto.MeasurementDTO;
import com.example.ProjectThreeRestApi.models.Measurement;
import com.example.ProjectThreeRestApi.models.Sensor;
import com.example.ProjectThreeRestApi.services.MeasurementService;
import com.example.ProjectThreeRestApi.util.*;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ProjectThreeRestApi.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.measurementService = measurementService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount() {
       return measurementService.findRainyDaysCount();
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurement() {
        return measurementService.findAllMeasurement().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {

        sensorValidator.validateSensor(convertToSensor(measurementDTO), bindingResult);

        if (bindingResult.hasErrors())
            returnErrorsToClient(bindingResult);

        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
   }

   private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
       return modelMapper.map(measurementDTO, Measurement.class);
   }

   private Sensor convertToSensor(MeasurementDTO measurementDTO) {
       Sensor sensor = new Sensor(measurementDTO.getSensor().getName());
       return sensor;
   }

   private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
       return modelMapper.map(measurement, MeasurementDTO.class);
   }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
