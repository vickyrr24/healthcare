package com.vignesh.healthcare.repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.vignesh.healthcare.models.Patient;

@Repository
public class PatientRepository {
    private final Map<Long, Patient> patients = new ConcurrentHashMap<>();
    private long currentId = 1;

    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(currentId++);
        }
        patients.put(patient.getId(), patient);
        return patient;
    }

    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patients.get(id));
    }
}
