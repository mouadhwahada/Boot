package tn.spring.pispring.Interfaces;

import tn.spring.pispring.Entities.AppointmentNut;
import tn.spring.pispring.Entities.Availability;
import tn.spring.pispring.Entities.User;

import java.util.List;
import java.util.Optional;

public interface AppointmentNutInterface {
    public List<AppointmentNut> getAllAppointments();
    public Optional<AppointmentNut> getAppointmentById(Long appointmentId);
    public AppointmentNut createAppointment(AppointmentNut appointment);
    public AppointmentNut updateAppointment(Long appointmentId, AppointmentNut appointmentDetails);
    public void deleteAppointment(Long appointmentId);
    public AppointmentNut assignAvailability(Long appointmentId, Long availabilityId);
    public AppointmentNut createAppointmentForNutritionist(AppointmentNut appointment, Long nutritionistId);
    public List<AppointmentNut> getAppointmentsForDate(String date);
    public void sendReminders();
    public AppointmentNut assignUserToAppointment(Long appointmentId, Long userId);
    public AppointmentNut assignNutritionistToAppointment(Long appointmentId, Long nutritionistId);
    public List<AppointmentNut> getAppointmentsForNutritionist(long nutritionistid);
    public List<AppointmentNut> getAppointmentsForClient(Long clientId);
}
