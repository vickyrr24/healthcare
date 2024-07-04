package com.vignesh.healthcare.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.vignesh.healthcare.interfaces.AppointmentService;
import com.vignesh.healthcare.models.Appointment;
import com.vignesh.healthcare.repository.AppointmentRepository;

@Service
@Primary
public class GeneralAppointmentService implements AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private static final Logger logger = LoggerFactory.getLogger(GeneralAppointmentService.class);

    GeneralAppointmentService(@Autowired AppointmentRepository appointmentRepo){
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Appointment bookAppointment(Appointment appointment){
        logger.trace(" booking appointment reached ... ");
        return appointmentRepo.save(appointment);
    }
    
    @Override
    public List<Appointment> getAppoinmentsByPatientId(Long patiendId){
        logger.trace(" getting appointment by patient Id reached ... ");
        return appointmentRepo.findByPatientId(patiendId);
    }

    @Override
    public Optional<Appointment> rescheduleAppointment(Long id,Appointment newAppointment){
        logger.trace(" rescheduleAppointment reached ... ");
        return appointmentRepo.findById(id).map(appointment ->{
            appointment.setId(id);
            appointment.setAppointmentTime(newAppointment.getAppointmentTime());
            appointment.setDoctorName(newAppointment.getDoctorName());
            return appointmentRepo.save(appointment);
        });
    }

    @Override
    public void cancelAppointment(Long id) {
        logger.trace(" cancel appointment reached ... ");
        appointmentRepo.deleteById(id);
    }
}
