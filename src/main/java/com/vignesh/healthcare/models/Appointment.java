package com.vignesh.healthcare.models;

import lombok.Data;

@Data
public class Appointment {
    private Long id;
    private Long patientId;
    private String appointmentTime;
    private String doctorName;
}
