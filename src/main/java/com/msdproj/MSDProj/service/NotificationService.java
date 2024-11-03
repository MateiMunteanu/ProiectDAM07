package com.msdproj.MSDProj.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // Method to send email notifications
    public void sendEmail(String to, String subject, String message) {
        // Logic to send email notification
        System.out.println("Sending email to " + to + ": " + subject + "\n" + message);
    }

    // Method to send SMS notifications
    public void sendSms(String phoneNumber, String message) {
        // Logic to send SMS notification
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    // Example method for sending a promotional offer
    public void sendPromotionalOffer(String clientEmail, String offerDetails) {
        String subject = "Special Offer Just for You!";
        String message = "Dear Client,\n\n" + offerDetails + "\n\nBest regards,\nCar Dealership";
        sendEmail(clientEmail, subject, message);
    }

    // Example method for sending a service appointment reminder
    public void sendServiceReminder(String clientEmail, String appointmentDetails) {
        String subject = "Service Appointment Reminder";
        String message = "Dear Client,\n\nThis is a reminder for your upcoming service appointment:\n" + appointmentDetails + "\n\nBest regards,\nCar Dealership";
        sendEmail(clientEmail, subject, message);
    }
}

