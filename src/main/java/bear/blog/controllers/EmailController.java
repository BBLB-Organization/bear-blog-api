package bear.blog.controllers;

import bear.blog.services.EmailService;
import bear.blog.services.VerificationCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin("*")
public class EmailController {

    private EmailService emailService;
    private VerificationCodeService verificationCodeService;

    public EmailController(EmailService emailService, VerificationCodeService verificationCodeService){
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
    }

    @PutMapping
    public ResponseEntity sendEmail(@RequestParam String emailAddress){
        ResponseEntity response;
        Boolean isEmailValid = this.verificationCodeService.checkIfValidEmailAddress(emailAddress);
        if(isEmailValid) {
            String subject = "Forgot Password: Verification Code Provided";
            Integer verificationCode = this.verificationCodeService.createVerificationCode(emailAddress);
            String body = "VERIFICATION CODE FOR FORGOT PASSWORD: " + verificationCode;
            this.emailService.sendEmail(emailAddress, subject, body);
            response = new ResponseEntity("Email was successfully sent to: " + emailAddress, HttpStatus.OK);
            return response;
        }
        else{
            return new ResponseEntity("Email was not found", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/check-verification-code")
    public ResponseEntity checkVerificationCode(@RequestParam String userEmailAddress, @RequestParam Integer userGeneratedVerificationCode){
        ResponseEntity response;
        Boolean verificationCodeStatus = this.verificationCodeService.checkVerificationCode(userEmailAddress, userGeneratedVerificationCode);
        if(verificationCodeStatus){
            response = new ResponseEntity("Verification Code is CORRECT", HttpStatus.OK);
        }
        else{
            response = new ResponseEntity("Verification code is INCORRECT!", HttpStatus.OK);
        }
        return response;
    }

}
