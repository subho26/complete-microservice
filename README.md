# complete-microservice


Zookeeper start	bin\windows\zookeeper-server-start.bat config\zookeeper.properties
Server start	bin\windows\kafka-server-start.bat config\server.properties
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 -topic order-topic
Kafka listener	kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic order-topic
