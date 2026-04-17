package com.resumeai;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ResumeController {

    @GetMapping("/")
    public String home() {
        return "Backend Running Successfully";
    }
}
