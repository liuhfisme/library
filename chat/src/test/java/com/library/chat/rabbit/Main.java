package com.library.chat.rabbit;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * @ClassName: Main
 * @Description: 测试连接
 * @author feifei.liu
 * @date 2018/11/27 11:51
 */
public class Main {
    private final static String IP = "192.168.20.201";
    private final static int PORT = 5672;
    private final static String USER_NAME = "admin";
    private final static String PASS_WORD = "admin";
    public static void main(String[] args) throws InterruptedException {
        com.rabbitmq.client.ConnectionFactory rabbitCf = new com.rabbitmq.client.ConnectionFactory();
        rabbitCf.setHost(IP);
        rabbitCf.setPort(PORT);
        rabbitCf.setUsername(USER_NAME);
        rabbitCf.setPassword(PASS_WORD);

        ConnectionFactory cf = new CachingConnectionFactory(rabbitCf);

        RabbitAdmin admin = new RabbitAdmin(cf);

        //创建队列
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);
        //创建topic类型的交换机
        TopicExchange exchange = new TopicExchange("myExchange");
        admin.declareExchange(exchange);
        //交换机和队列绑定，路由规则为匹配“foo.”开头的路由键
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.*"));

        //设置监听
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
        Object listener = new Object() {
            public void handleMessage(String foo) {
                System.out.println(String.format("[x] Received '%s'", foo));
            }
        };

        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myQueue");
        container.start();

        //发送消息
        RabbitTemplate template = new RabbitTemplate(cf);
        template.convertAndSend("myExchange", "foo.bar", "Hello World!");
        Thread.sleep(3000);
        container.stop();

    }
}
