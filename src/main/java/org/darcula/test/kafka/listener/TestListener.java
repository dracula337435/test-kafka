package org.darcula.test.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author dk
 */
@Component
public class TestListener {

    private static Logger logger = LoggerFactory.getLogger(TestListener.class);

    @KafkaListener(topics = {"some-topic"}, groupId = "some-group")
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("接收到消息");
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
    }

}
