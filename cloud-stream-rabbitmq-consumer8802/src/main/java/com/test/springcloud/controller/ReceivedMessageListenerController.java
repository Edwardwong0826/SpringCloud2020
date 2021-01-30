package com.test.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class) //消费者接收
public class ReceivedMessageListenerController
{
    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT) //listen
    public void input(Message<String> message)
    {
        System.out.println("消费者1号， -----> 接受到的消息： " + message.getPayload()
                + "\t port: " + serverPort);
    }
}