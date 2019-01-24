package org.darcula.test.kafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.darcula.test.kafka.WrapperDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dk
 */
@RestController
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(@RequestParam("msg") String msg){
        logger.info("将要发送消息="+msg);
        ListenableFuture future = kafkaTemplate.send("some-topic", msg);
        future.addCallback(
                o -> logger.info("send-消息发送成功：" + msg),
                throwable -> logger.info("消息发送失败：" + msg));
        return "hello, msg="+msg;
    }

    @GetMapping("/invoke")
    public WrapperDTO invoke(@RequestParam("interfaceName") String interfaceName, @RequestParam("methodName") String methodName) throws Exception{

        WrapperDTO wrapperDTO = new WrapperDTO();
        wrapperDTO.setInterfaceName(interfaceName);
        wrapperDTO.setMethodName(methodName);

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(wrapperDTO);
        logger.info("将要发送消息="+valueAsString);
        ListenableFuture future = kafkaTemplate.send("invoke-topic", valueAsString);
        future.addCallback(
                o -> logger.info("send-消息发送成功：" + valueAsString),
                throwable -> logger.info("消息发送失败：" + valueAsString));
        return wrapperDTO;
    }

}
