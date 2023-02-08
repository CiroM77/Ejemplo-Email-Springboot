/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.exampleEmail.EmailExample.Service;

import com.exampleEmail.EmailExample.Model.NotificationEmail;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ciro-
 */
public interface NotificacionServicio {
    boolean sendTextNotificationEmail(NotificationEmail notificationEmail);
    boolean sendNotificationEmailWithAttachment(NotificationEmail notificationEmail,
                                             MultipartFile file);
}
