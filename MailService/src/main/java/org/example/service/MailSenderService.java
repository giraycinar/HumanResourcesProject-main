package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.rabbitmq.model.RegisterMailModel;
import org.example.rabbitmq.model.RegisterMailModel2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;


//    @EventListener(ApplicationReadyEvent.class) Uygulama ayaga kalktiginda metodun bir kerelik calistirilmasi icin.
    public void sendMail(RegisterMailModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("humanrecorcues@gmail.com");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("AKTIVASYON KODUNUZ...");
        mailMessage.setText(model.getUsername()+" Aramıza hoşgeldin!\n"+"Hesabınızı doğrulamak için aktivasyon kodunuz: "+model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
    public void sendPasswordResetEmail(String recipientEmail, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Parola Sıfırlama");
        message.setText("Merhaba,\n\nParolanızı sıfırlamak için aşağıdaki linke tıklayın:\n\n"
                + "http://yourwebsite.com/reset-password?token=" + resetToken);

        javaMailSender.send(message);
        System.out.println("Parola sıfırlama maili gönderildi.");
    }
    public void sendWelcomeMail(RegisterMailModel2 model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("humanrecorcues@gmail.com");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Hosgeldiniz!");
        mailMessage.setText(model.getUsername()+" Aramıza hoşgeldiniz!\n");
        javaMailSender.send(mailMessage);
    }


}
