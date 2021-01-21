package com.cjx.rabbitmq.plan.listener;

import com.cjx.rabbitmq.plan.config.DelayMQConfig;
import com.cjx.rabbitmq.plan.dto.PlanDTO;
import com.cjx.rabbitmq.plan.service.PlanService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
*@Description 消息通知监听器
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@Component
public class NotifyListener {

    @Autowired
    PlanService planService;

    @RabbitListener(queues = DelayMQConfig.DELAY_QUEUE_NAME)
    @RabbitHandler
    public void onMessage(PlanDTO msg, Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //通知用户
            planService.notifyUser(msg.getId(),msg.getVersion());
            channel.basicAck(deliveryTag, false);
            //System.out.println("消息被确认！");
        } catch (IOException e) {
            channel.basicNack(deliveryTag, false, false);
           // System.out.println("消息被否定确认！");
        }
    }
}
