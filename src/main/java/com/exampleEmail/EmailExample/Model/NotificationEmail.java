/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exampleEmail.EmailExample.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ciro-
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail {
    private String to;
    private String cc;
    private String subject;
    private String bodyMsg;
    private MultipartFile file;
    private boolean isHtmlMsg;
}
