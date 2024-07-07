package com.vignesh.healthcare.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Appointment {
    private Long id;
    private Long patientId;
    private String appointmentTime;
    private String doctorName;
}
