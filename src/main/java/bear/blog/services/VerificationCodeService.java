package bear.blog.services;

import bear.blog.models.VerificationCode;
import bear.blog.repositories.VerificationRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerificationCodeService {

    private VerificationRepository verificationRepository;

    public VerificationCodeService(VerificationRepository verificationRepository){
        this.verificationRepository = verificationRepository;
    }

    public Integer createVerificationCode(String emailAddress){
        Integer verificationCode = 0;
        VerificationCode currentUser = this.verificationRepository.findByEmailAddress(emailAddress);
        Integer randomVerificationCode = generateRandomCode();
        if(currentUser == null){
            //Do something in the event if someone is forgetting a password with wrong email address.
        }
        else if(currentUser != null){
            currentUser.setVerificationCode(randomVerificationCode);
            currentUser.setHasVerificationCode(true);
            this.verificationRepository.save(currentUser);
            verificationCode = randomVerificationCode;
        }
        return verificationCode;
    }

    public Boolean checkVerificationCode(String emailAddress, Integer userCreatedVerificationCode){
        VerificationCode currentUser = this.verificationRepository.findByEmailAddress(emailAddress);
        Boolean hasUserInputVerificationCode = currentUser.getHasVerificationCode();
        //Checks if user has already inputted verification code before
        if(hasUserInputVerificationCode) {
            Integer computerGeneratedVerificationCode = currentUser.getVerificationCode();
            if (computerGeneratedVerificationCode.equals(userCreatedVerificationCode)) {
                currentUser.setHasVerificationCode(false);
                this.verificationRepository.save(currentUser);
                return true;
            } else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public Boolean checkIfValidEmailAddress(String emailAddress){
        VerificationCode currentUser = this.verificationRepository.findByEmailAddress(emailAddress);
        if(currentUser == null ||currentUser.getEmailAddress() == null){
            return false;
        }
        else{
            return true;
        }
    }

    public Integer generateRandomCode(){
        Random randomNumber = new Random();
        Integer min = 100000;
        Integer max = 999999;
        Integer randomVerificationCode = randomNumber.nextInt(max - min + 1) + min;
        return randomVerificationCode;
    }

}
