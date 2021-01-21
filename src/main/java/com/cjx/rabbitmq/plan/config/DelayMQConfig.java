package com.cjx.rabbitmq.plan.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
*@Description 延迟交换机相关配置
*@Verson v1.0.0
*@Author cjunxian
*@Date
*/
@Configuration
public class DelayMQConfig {

    /**
     * 交换机名称
     */
    public final static String DELAY_EXCHANGE_NAME = "delay_exchange";

    /**
     * 队列名称
     */
    public final static String DELAY_QUEUE_NAME = "delay_queue";

    /**
     * 路由key 不是topic不能使用通配符
     */
    public final static String DELAY_ROUTE_KEY = "delay.notify";

    /**
     * 延迟交换机
     */
    @Bean("delayExchange")
    public CustomExchange delayExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME,"x-delayed-message",true,true,args);
    }

    /**
     * 延迟队列
     */
    @Bean("delayQueue")
    public Queue delayQueue(){
        return new Queue(DELAY_QUEUE_NAME,true,false,false);
    }


    @Bean
    public Binding bindingDelayExchangeQueue(@Qualifier("delayExchange") Exchange exchange,@Qualifier("delayQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_ROUTE_KEY).noargs();
    }
}
