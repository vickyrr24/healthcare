package com.vignesh.healthcare.repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vignesh.healthcare.models.Appointment;

@Repository
public class AppointmentRepository {
    private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>(0);
    private static final Logger logger = LoggerFactory.getLogger(AppointmentRepository.class);
    private long currentId = 1;

    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(currentId++);
        }
        appointments.put(appointment.getId(), appointment);
        logger.info(" Appointment Created ... ");
        return appointment;
    }

    public List<Appointment> findByPatientId(Long patientId) {
        logger.info(" finding patient Id {} " , patientId);
        return appointments.values().stream()
                .filter(appointment -> Objects.equals(appointment.getPatientId(), patientId))
                .collect(Collectors.toList());
    }

    public Optional<Appointment> findById(Long id) {
        logger.info(" cancelling appointment {}" ,id);
        return Optional.ofNullable(appointments.get(id));
    }

    public void deleteById(Long id) {
        logger.info(" cancelling appointment {}" ,id);
        appointments.remove(id);
    }
}