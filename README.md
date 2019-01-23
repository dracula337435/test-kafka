# 试验kafka


## 试验用法
有可用的producer和consumer
producer的使用方式为，发送GET请求到```http://localhost:8080/sendMsg?msg=[要发送的msg]```

## 踩坑
windows下使用docker方式启动的kafka，可能会有连接不上的问题，报错
```
2019-01-23 09:37:29.399  WARN 25040 --- [           main] org.apache.kafka.clients.NetworkClient   : [Consumer clientId=consumer-1, groupId=some-group] Connection to node 1 could not be established. Broker may not be available.
```
应修改```docker-compose.yml```中```kafka```的环境变量```KAFKA_ADVERTISED_HOST_NAME```为docker虚拟机的ip，这里为```192.168.99.100```