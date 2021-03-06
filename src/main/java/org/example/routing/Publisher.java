package org.example.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author yibozhang
 * @date 2020/4/17 3:44 下午
 */
public class Publisher {


    public static final String EXCHANGE_NAME = "logs2";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            String severity = getSeverity(args);
            String message = getMessage(args);
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    private static String getSeverity(String[] args) {
        return args[0];
    }

    private static String getMessage(String[] args) {
        return args[1];
    }

}
