package bear.blog.services;

import bear.blog.models.Users;
import bear.blog.repositories.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public Users registerUser(Users user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setLoggedIn(false);
        return this.usersRepository.save(user);
    }

    public boolean checkLoginCredentials(String email, String userProvidedPassword) {
        Users user = usersRepository.findByEmailAddress(email);
        if (user != null) {
            String encodedPassword = user.getPassword();
            if (passwordEncoder().matches(userProvidedPassword, encodedPassword)) {
                user.setLoggedIn(true);
                usersRepository.save(user);
                return true;
            }
            else{
                user.setLoggedIn(false);
                usersRepository.save(user);
                return false;
            }
        }
        else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }

    public Users getUserByEmailAddress(String emailAddress){
        Users user = this.usersRepository.findByEmailAddress(emailAddress);
        return user;
    }

    public Boolean checkIfUserLoggedIn(String emailAddress){
        Users user = this.usersRepository.findByEmailAddress(emailAddress);
        Boolean loggedIn = (user != null) ? user.getLoggedIn() : false;
        return loggedIn;
    }

    public Boolean logoutCurrentUser(String emailAddress){
        Users currentUser = this.usersRepository.findByEmailAddress(emailAddress);
        currentUser.setLoggedIn(false);
        this.usersRepository.save(currentUser);
        return currentUser.getLoggedIn();
    }


}
