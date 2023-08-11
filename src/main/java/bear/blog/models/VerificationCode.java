package bear.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    String emailAddress;
    Integer verificationCode;
    Boolean hasVerificationCode;
    Boolean hasChangePasswordAuthorization;

    public VerificationCode(){}

    public VerificationCode(Integer id, String emailAddress, Integer verificationCode, Boolean hasVerificationCode, Boolean hasChangePasswordAuthorization) {
        this.id = id;
        this.emailAddress = emailAddress;
        this.verificationCode = verificationCode;
        this.hasVerificationCode = hasVerificationCode;
        this.hasChangePasswordAuthorization = hasChangePasswordAuthorization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Integer getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(Integer verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getHasVerificationCode() {
        return hasVerificationCode;
    }

    public void setHasVerificationCode(Boolean hasVerificationCode) {
        this.hasVerificationCode = hasVerificationCode;
    }

    public Boolean getHasChangePasswordAuthorization() {
        return hasChangePasswordAuthorization;
    }

    public void setHasChangePasswordAuthorization(Boolean hasChangePasswordAuthorization) {
        this.hasChangePasswordAuthorization = hasChangePasswordAuthorization;
    }
}
