package com.mail.server.controller;

import com.mail.server.Service.MailService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    private MailService mailService;


    @RequestMapping("/mail/send")
    public String sendmail(String email) throws MessagingException {
        mailService.sendmail(email);
        return mailService.returnsentcode();
    }
}
