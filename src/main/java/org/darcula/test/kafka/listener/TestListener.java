package org.darcula.test.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.darcula.test.kafka.WrapperDTO;
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

    @KafkaListener(topics = {"invoke-topic"}, groupId = "some-group")
    public void listenInvoke(ConsumerRecord<?, ?> record) throws Exception {
        logger.info("接收到invoke-topic");
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value().toString());
        logger.info("反序列化一下");
        logger.info(new ObjectMapper().readValue(record.value().toString(), WrapperDTO.class).toString());
    }

}
