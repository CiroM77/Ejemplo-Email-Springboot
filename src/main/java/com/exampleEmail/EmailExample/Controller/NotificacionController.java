/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exampleEmail.EmailExample.Controller;

import com.exampleEmail.EmailExample.Model.NotificationEmail;
import com.exampleEmail.EmailExample.Service.NotificacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ciro-
 */
@RestController
public class NotificacionController {
    @Autowired
    NotificacionServicio notificationService;

    @PostMapping("/sendNotificationEmail")
    public ResponseEntity<String> sendNotificationEmail(@ModelAttribute NotificationEmail notificationEmail) {
        boolean emailSent = false;
        if (notificationEmail.getFile()!=null) {
            emailSent = notificationService.sendNotificationEmailWithAttachment(notificationEmail, notificationEmail.getFile());
        } else {
            emailSent = notificationService.sendTextNotificationEmail(notificationEmail);
        }
        if (emailSent) {
            return ResponseEntity.ok("Email enviado con exito");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Se ha producidod un error al enviar el email.");
        }
    }
}
