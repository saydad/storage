package self.start.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import self.start.config.RabbitConstant;

import java.util.List;
import java.util.Map;

/**
 * create by liuyong4 2018/5/11 下午12:03
 **/
@Component
public class RabbitReceive {

    @RabbitListener(queues = RabbitConstant.QUEUE_NAME, containerFactory = "customRabbitListenerContainerFactory")
    public void handleMsg(List<Message> message) {
        System.out.println("--分割线--" + message.size() + "--分割线--");
    }
}
