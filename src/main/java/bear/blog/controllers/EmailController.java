package bear.blog.controllers;

import bear.blog.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping
    public ResponseEntity sendEmail(){
        ResponseEntity response;
        String emailAddressTo = "sample_gmail@gmail.com";
        String subject = "Testing Spring Boot";
        String body = "Testing Spring Boot capability to send emails";
        this.emailService.sendEmail(emailAddressTo, subject, body);
        response = new ResponseEntity("Email was successfully sent to: " +emailAddressTo + "SUBJECT: "+subject + "BODY: "+body, HttpStatus.OK);
        return response;
    }
}
