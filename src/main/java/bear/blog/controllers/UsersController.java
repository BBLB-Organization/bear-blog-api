package bear.blog.controllers;

import bear.blog.models.Users;
import bear.blog.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@CrossOrigin("*")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity checkIfUserLoggedIn(@RequestParam String emailAddress){
        Boolean loggedIn = this.usersService.checkIfUserLoggedIn(emailAddress);
        return new ResponseEntity(loggedIn, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserEmailAddressById(@PathVariable Integer id){
        Users currentUser = this.usersService.getUserById(id);
        if(currentUser != null){
            String emailAddress = currentUser.getEmailAddress();
            return new ResponseEntity(emailAddress, HttpStatus.OK);
        }
        else{
            return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("register-user")
    public ResponseEntity registerUser(@RequestBody Users users){
        Users user = this.usersService.registerUser(users);

        HttpStatus status;
        ResponseEntity response;
        if(user != null){
            status = HttpStatus.OK;
            response = new ResponseEntity(user,status);
        }else{
            status  = HttpStatus.BAD_REQUEST;
            response = new ResponseEntity("USER NOT ADDED",status);
        }
        return response;
    }

    @PostMapping(path = "login", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkLoginCredentials(@RequestBody Users user) {
        try {
            if (usersService.checkLoginCredentials(user.getEmailAddress(), user.getPassword())) {
                Users loggedInUser = usersService.getUserByEmailAddress(user.getEmailAddress());
                loggedInUser.setId(null);
                loggedInUser.setEmailAddress("");
                loggedInUser.setPassword("");
                loggedInUser.setFirstName("");
                loggedInUser.setLastName("");
                loggedInUser.setLoggedIn(true);
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Incorrect Email or Password", HttpStatus.UNAUTHORIZED);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("logout")
    public ResponseEntity logoutCurrentUser(@RequestParam String emailAddress){
        Boolean logoutCurrentUser = this.usersService.logoutCurrentUser(emailAddress);
        return new ResponseEntity(logoutCurrentUser, HttpStatus.OK);
    }

}
