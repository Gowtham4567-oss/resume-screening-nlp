package com.resumeai;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        if(user.username.equals("admin") && user.password.equals("1234")) {
            return "Login Success";
        }

        return "Invalid Credentials";
    }
}
