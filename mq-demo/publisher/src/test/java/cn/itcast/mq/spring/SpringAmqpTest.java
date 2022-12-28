package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author power
 * @Date 2022/12/18 16:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message__";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() {
        // 交换机名称
        String exchangeName = "power.fanout";
        // 消息
        String message = "hello,every one!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }


    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "power.direct";
        // 消息
        String message = "hello,every red!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }



    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "power.topic";
        // 消息
//        String message = "power学习Java";
        String message = "今天天气真不错";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
    }


    @Test
    public void testSendObjectExchange() {
        Map<Object, Object> msg = new HashMap<>();
        msg.put("name", "柳岩");
        msg.put("age", "21");
        rabbitTemplate.convertAndSend("object.queue",msg);
    }
}
