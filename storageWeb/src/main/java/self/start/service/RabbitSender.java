package self.start.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import self.start.config.RabbitConstant;

import java.util.Map;

/**
 * rabbit sender
 * create by liuyong4 2018/5/11 上午11:38
 **/
@Component
public class RabbitSender  {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMsg(Map<String, String> content) {
        rabbitTemplate.convertAndSend(RabbitConstant.EXCHANGE_NAME, RabbitConstant.ROUTING_KEY, content);
    }
}
