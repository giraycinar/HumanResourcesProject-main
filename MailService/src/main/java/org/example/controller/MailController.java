package org.example.controller;

import org.example.dto.Request.ResetEmailRequest;
import org.example.rabbitmq.model.RegisterMailModel2;
import org.example.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@RequestMapping("/mail")
public class MailController implements WebMvcConfigurer {

    private final MailSenderService mailService;
    @Autowired
    public MailController(MailSenderService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/send-reset-email")
    @CrossOrigin("*")
    public String sendResetEmail(@RequestBody ResetEmailRequest request) {
        String recipientEmail = request.getEmail();
        String resetToken = "A43875";
        mailService.sendPasswordResetEmail(recipientEmail, resetToken);
        return "Parola sıfırlama e-postası gönderildi.";
    }//Sabit token kullandım. İstenilirse token oluşturulabilir.

    @GetMapping("/send-welcome-email")
    @CrossOrigin("*")
    public String sendWelcomeEmail(@RequestBody RegisterMailModel2 request) {
        mailService.sendWelcomeMail(request);
        return "Hoşgeldin e-postası gönderildi.";
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

}