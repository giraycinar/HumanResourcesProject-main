package org.example.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.rabbitmq.model.RegisterMailModel;
import org.example.service.MailSenderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterMailConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "${rabbitmq.queue-mail-sender}")
    public void sendActivationCode(RegisterMailModel model){
        log.info("Model {}",model.toString());
        mailSenderService.sendMail(model);
    }

}
