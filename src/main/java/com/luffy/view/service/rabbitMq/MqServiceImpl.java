package com.luffy.view.service.rabbitMq;

import com.luffy.view.enums.MqTypeEnum;
import com.luffy.view.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqServiceImpl implements MqService {

    @Autowired
    Producter producter;

    @Autowired
    DelayQueueProducter delayQueueProducter;

    @Override
    public void product(Integer exchange, String routKey, String message) {
        if (exchange.equals(MqTypeEnum.RABBIT_DEFAULT.getType())) {
            producter.send(null, "defaultQueue", message);
        }
        if (exchange.equals(MqTypeEnum.RABBIT_DIRECT.getType())) {
            producter.send("myDirectExchange", routKey, message);
        }
        if (exchange.equals(MqTypeEnum.RABBIT_FANOUT.getType())) {
            producter.send("myFanoutExchange", routKey, message);
        }
        if (exchange.equals(MqTypeEnum.RABBIT_TOPIC.getType())) {
            producter.send("topic-news-exchange", routKey, message);
        }
    }

    @Override
    public void delayProduct(String message) {
        delayQueueProducter.send(message);
    }

}
