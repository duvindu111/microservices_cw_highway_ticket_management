package lk.ijse.gdse66.user_service.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.user_service.dto.CredentialDTO;
import lk.ijse.gdse66.user_service.dto.UserDTO;
import lk.ijse.gdse66.user_service.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    @PatchMapping("/update")
    public String updateUserDetails(@Valid @RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @GetMapping("/verify")
    public boolean verifyCredentials(@Valid @RequestBody CredentialDTO credentialDTO){
        return userService.checkCredentials(credentialDTO);
    }

    @GetMapping("/find_by_email/{email}")
    public UserDTO verifyCredentials(@PathVariable("email") String email){
        return userService.findUserByEmail(email);
    }
}
