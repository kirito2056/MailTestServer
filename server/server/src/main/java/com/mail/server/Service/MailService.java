package com.mail.server.Service;

import jakarta.mail.MessagingException;

public interface MailService {
    String createcode();

    String sendmail(String email) throws MessagingException, MessagingException;

    String returnsentcode();
}
