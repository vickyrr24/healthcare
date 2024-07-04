package com.vignesh.healthcare.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vignesh.healthcare.models.Appointment;

@Component
public interface AppointmentService {
    public Appointment bookAppointment(Appointment appointment);
    public List<Appointment> getAppoinmentsByPatientId(Long patiendId);
    public Optional<Appointment> rescheduleAppointment(Long id,Appointment newAppointment);
    public void cancelAppointment(Long id);
}
