package com.resumeai;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ChatbotController {

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return "AI Response: You said -> " + message;
    }
}
