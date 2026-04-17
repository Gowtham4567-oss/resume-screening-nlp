package com.resumeai;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin("*")
public class ChatbotController {

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {

        message = message.toLowerCase();

        // 👋 Greetings
        if(message.contains("hello") || message.contains("hi")) {
            return "Hello 👋 Welcome to AI Resume Analyzer!";
        }

        // 🎯 Skills Suggestion
        if(message.contains("skills")) {
            return "Top Skills: Java, Python, SQL, AI, Machine Learning, Web Development";
        }

        // 💼 Interview Questions
        if(message.contains("interview")) {
            return "Sample Questions:\n1. Tell me about yourself\n2. What are your strengths?\n3. Explain your project";
        }

        // 📄 Resume Tips
        if(message.contains("resume")) {
            return "Tips:\n✔ Add strong skills\n✔ Use bullet points\n✔ Keep it 1 page\n✔ Highlight projects";
        }

        // 🤖 AI Response (Default)
        return "AI Response: I understand -> " + message;
    }
}
