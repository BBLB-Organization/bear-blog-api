package bear.blog.controllers;

import bear.blog.models.Users;
import bear.blog.services.EmailService;
import bear.blog.services.UsersService;
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
    private UsersService usersService;

    public EmailController(EmailService emailService, VerificationCodeService verificationCodeService, UsersService usersService){
        this.emailService = emailService;
        this.verificationCodeService = verificationCodeService;
        this.usersService = usersService;
    }

    @PutMapping
    public ResponseEntity sendForgotPasswordEmail(@RequestParam String emailAddress){
        ResponseEntity response;
        Boolean isEmailValid = this.verificationCodeService.checkIfValidEmailAddress(emailAddress);
        if(isEmailValid) {
            Users currentUser = usersService.getUserByEmailAddress(emailAddress);
            String subject = "Forgot Password: Verification Code Provided";
            Integer verificationCode = this.verificationCodeService.createVerificationCode(emailAddress);
            String body = "VERIFICATION CODE FOR FORGOT PASSWORD: " + verificationCode + "<br>"
                    + "Please click on the following link to route to the verification page: "
                    + "<a href=\"https://bblb-organization.github.io/bear-blog-website/"
                    + currentUser.getId() + "/user-verification\">https://bblb-organization.github.io/bear-blog-website/"+currentUser.getId()+"/user-verification</a>";
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
            response = new ResponseEntity(verificationCodeStatus, HttpStatus.OK);
        }
        else{
            response = new ResponseEntity(verificationCodeStatus, HttpStatus.OK);
        }
        return response;
    }

}
