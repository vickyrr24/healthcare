package com.vignesh.healthcare;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vignesh.healthcare.controllers.AppointmentController;
import com.vignesh.healthcare.interfaces.AppointmentService;
import com.vignesh.healthcare.models.Appointment;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Test
    void testBookAppointment_Success() throws Exception {
        // Arrage
        Appointment appointment = Appointment.builder()
                .id(1L)
                .patientId(2L)
                .doctorName("John")
                .appointmentTime("22-July-2024")
                .build();

        // Act
        Mockito.when(appointmentService.bookAppointment(appointment)).thenReturn(appointment);

        // Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/appointments").content(asJsonString(appointment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("John"));
    }

    @Test
    void testGetAppointments_Success() throws Exception {

        Appointment appointment = Appointment.builder()
                .id(1L)
                .patientId(2L)
                .doctorName("John")
                .appointmentTime("22-July-2024")
                .build();
        List<Appointment> lst = new ArrayList<>();
        lst.add(appointment);

        Mockito.when(appointmentService.getAppoinmentsByPatientId(1L)).thenReturn(lst);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/appointments").param("patientId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testRescheduleAppointment_Success() throws Exception {
        Appointment appointment = Appointment.builder()
                .id(1L)
                .patientId(2L)
                .doctorName("John")
                .appointmentTime("22-July-2024")
                .build();

        Optional<Appointment> optAppointment = Optional.of(appointment);
        Mockito.when(appointmentService.rescheduleAppointment(1L, appointment)).thenReturn(optAppointment);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/appointments/{appointmentId}", "1").content(asJsonString(appointment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    void testCancelAppointment_Success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/appointments/{appointmentId}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
