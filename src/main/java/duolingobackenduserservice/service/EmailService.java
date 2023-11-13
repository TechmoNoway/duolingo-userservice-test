package duolingobackenduserservice.service;

import duolingobackenduserservice.dto.EmailDetail;
import duolingobackenduserservice.dto.InputEmailData;

public interface EmailService {// Method
    // To send a simple email
    String sendSimpleMail(InputEmailData details, String template);

    // Method
    // To send an email with attachment
    String sendMailWithAttachment(InputEmailData details, String template);
}
