package com.vignesh.healthcare.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.vignesh.healthcare.interfaces.PatientService;
import com.vignesh.healthcare.models.Patient;
import com.vignesh.healthcare.repository.PatientRepository;

@Service
@Primary
public class GeneralPatientService implements PatientService{
    private PatientRepository patientRepo;

    GeneralPatientService(@Autowired PatientRepository patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Optional<Patient> getPatientById(Long patientId) {
        return patientRepo.findById(patientId);
    }

    public Patient createPatient(Patient patient){
        return patientRepo.save(patient);
    }
}
