/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exampleEmail.EmailExample.Service;

import com.exampleEmail.EmailExample.Model.NotificationEmail;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class NotificacionServiceImp implements NotificacionServicio{
   
    @Autowired
    private JavaMailSender mailSender;
      /**
     * Método para enviar email con un simple texto
     *
     * @param notificationEmail
     * @return boolean
     */
    @Override
    public boolean sendTextNotificationEmail(NotificationEmail notificationEmail) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(notificationEmail.getTo());
            mailMessage.setSubject(notificationEmail.getSubject());
            mailMessage.setText(notificationEmail.getBodyMsg());
            mailSender.send(mailMessage);
            return true;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return false;
        }
    }

    /**
     * Método para enviar email con HTML, archivo adjunto o simple texto
     *
     * @param notificationEmail
     * @param file
     * @return
     */
    @Override
    public boolean sendNotificationEmailWithAttachment(NotificationEmail notificationEmail,
                                                       MultipartFile file) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(notificationEmail.getTo());
            messageHelper.setSubject(notificationEmail.getSubject());

            if (notificationEmail.isHtmlMsg()) {
                // Para mensajes de texto con HTML, indica true
                //bodyMsg: <b>This is Notification email</b>
                messageHelper.setText(notificationEmail.getBodyMsg(), true);
            } else {
                /// Para simples mensajes de texto
                messageHelper.setText(notificationEmail.getBodyMsg());
            }

            messageHelper.addAttachment(file.getOriginalFilename(), file);
            mailSender.send(mimeMessage);
            return true;
        } catch (MessagingException ex) {
            log.info(ex.getMessage());
            return false;
        }
    }
}
