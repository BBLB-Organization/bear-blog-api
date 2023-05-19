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
        return this.usersRepository.save(user);
    }

    public boolean checkLoginCredentials(String email, String userProvidedPassword) {
        Users user = usersRepository.findByEmailAddress(email);
        if (user != null) {
            String encodedPassword = user.getPassword();
            if (passwordEncoder().matches(userProvidedPassword, encodedPassword)) {
                return true;
            }
            else{
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



}