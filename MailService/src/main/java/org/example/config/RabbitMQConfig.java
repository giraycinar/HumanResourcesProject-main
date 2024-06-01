package org.example.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    private String queueMailSender="mail-sender-queue";
    private String exchangeMailSender="mail-sender-exchange";
    private String routingKeyMailSender="mail-sender-routing-key";


    @Bean
    public Queue queueRegister(){
        Queue queue = new Queue(queueMailSender);
        return queue;
    }
    @Bean
    public TopicExchange exchange(){
        TopicExchange exchange = new TopicExchange(exchangeMailSender);
        return exchange;
    }
    @Bean
    public Binding binding(){
        Binding binding = BindingBuilder
                .bind(queueRegister())
                .to(exchange())
                .with(routingKeyMailSender);
        return binding;
    }



}