package com.example.ProjectThreeRestApi.repositories;

import com.example.ProjectThreeRestApi.models.Measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
}
