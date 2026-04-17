package com.resumeai;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class AdminController {

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> list = new ArrayList<>();

        User u1 = new User();
        u1.username = "admin";

        User u2 = new User();
        u2.username = "user1";

        list.add(u1);
        list.add(u2);

        return list;
    }
}
