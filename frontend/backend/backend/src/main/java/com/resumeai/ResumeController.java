package com.resumeai;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class ResumeController {

    @PostMapping("/analyze")
    public String analyze(@RequestParam("file") MultipartFile file) {
        return "Resume uploaded: " + file.getOriginalFilename();
    }
}
