package com.msdproj.MSDProj.controller;

import com.msdproj.MSDProj.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Endpoint to send a promotional offer to a client via email
    @PostMapping("/send-promotion")
    public ResponseEntity<String> sendPromotionalOffer(
            @RequestParam String email,
            @RequestParam String offerDetails) {
        notificationService.sendPromotionalOffer(email, offerDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Promotional offer sent to " + email);
    }

    // Endpoint to send a service appointment reminder to a client via email
    @PostMapping("/send-reminder")
    public ResponseEntity<String> sendServiceReminder(
            @RequestParam String email,
            @RequestParam String appointmentDetails) {
        notificationService.sendServiceReminder(email, appointmentDetails);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Service reminder sent to " + email);
    }

    // Endpoint to send a custom email notification
    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmailNotification(
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message) {
        notificationService.sendEmail(email, subject, message);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Email sent to " + email);
    }

    // Endpoint to send a custom SMS notification
    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSmsNotification(
            @RequestParam String phoneNumber,
            @RequestParam String message) {
        notificationService.sendSms(phoneNumber, message);
        return ResponseEntity.status(HttpStatus.OK)
                .body("SMS sent to " + phoneNumber);
    }
}

