package com.vignesh.healthcare.interfaces;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vignesh.healthcare.models.Patient;

@Component
public interface PatientService {
    public Optional<Patient> getPatientById(Long patientId);
    public Patient createPatient(Patient patient);
}
