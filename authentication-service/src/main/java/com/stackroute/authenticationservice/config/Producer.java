package com.stackroute.authenticationservice.config;


import com.stackroute.authenticationservice.config.RabbitMqDomain.UserDetails;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/producer")
public class Producer {

//    private TopicExchange topicExchange;
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public Producer(TopicExchange topicExchange, RabbitTemplate rabbitTemplate) {
//        this.topicExchange = topicExchange;
//        this.rabbitTemplate = rabbitTemplate;
//    }
//@PostMapping("/tutor")
//public String sendTutorMessage(@RequestBody UserDetails userDetails){
//    rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY,userDetails);
//    return " adding tutor queue is success!!";
//}
//@PostMapping("/student")
//    public String sendStudentMessage(@RequestBody UserDetails userDetails){
//        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE,MessageConfig.ROUTING_KEY2,userDetails);
//        return " adding student queue is success!!";
//    }
}
