package org.example.rabbitmq.producer;

import lombok.RequiredArgsConstructor;
import org.example.rabbitmq.model.RegisterMailModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMailProducer {

    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.key-mail-sender}")
    private String bindingKeyMailSender;

    private final RabbitTemplate rabbitTemplate;

    public void sendActivationCode(RegisterMailModel model){
        rabbitTemplate.convertAndSend(directExchange,bindingKeyMailSender,model);
    }
}