package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

/**
 * @author power
 * @Date 2022/12/18 16:39
 */
@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenerSimpleQueue(String message) {
//        System.out.println("消费者接收到simple.queue的消息：【" + message + "】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenerWorkkQueue1(String message) throws InterruptedException {
        System.out.println("消费者1接收到的消息：【" + message + "】" + LocalTime.now());
        Thread.sleep(20);
    }
    @RabbitListener(queues = "simple.queue")
    public void listenerWorkkQueue2(String message) throws InterruptedException {
        System.err.println("消费者2接收到的消息：【" + message + "】" + LocalTime.now());
        Thread.sleep(200);
    }


    @RabbitListener(queues = "fanout.queue1")
    public void listenerFanoutQueue1(String message) {
        System.out.println("消费者接收到fanout.queue1的消息：【" + message + "】");
    }


    @RabbitListener(queues = "fanout.queue2")
    public void listenerFanoutQueue2(String message) {
        System.out.println("消费者接收到fanout.queue2的消息：【" + message + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "power.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String message) {
        System.out.println("direct.queue1的消息：【" + message + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "power.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "yellow"}
    ))
    public void listenDirectQueue2(String message) {
        System.out.println("direct.queue2的消息：【" + message + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "power.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String message) {
        System.out.println("topic.queue1的消息：【" + message + "】");
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "power.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String message) {
        System.out.println("topic.queue2的消息：【" + message + "】");
    }

    @RabbitListener(queues = "object.queue")
    public void ListenObjectQueue(Map<String, Object> msg) {
        System.out.println("object.queue的消息：【" + msg + "】");
    }
}
