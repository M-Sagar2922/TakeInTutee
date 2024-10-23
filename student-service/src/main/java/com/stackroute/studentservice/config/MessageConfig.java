package com.stackroute.studentservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    //public static final String Queue = "Tutor_Queue";
    public static final String Queue2 = "Student_Queue";
    public static final String EXCHANGE = "Authenticate_Exchange";

    //public static final String ROUTING_KEY = "Tutor_RoutingKey";

   public static final String ROUTING_KEY2 = "Student_RoutingKey";


//    @Bean
//    public org.springframework.amqp.core.Queue queue() {
//        return new Queue(Queue);
//
//    }

    @Bean
    public Queue queue2() {
        return new Queue(Queue2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

//    @Bean
//    public Binding binding(Queue queue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
//
//    }

    @Bean
    public Binding binding2(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue2).to(topicExchange).with(ROUTING_KEY2);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}