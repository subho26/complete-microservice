package com.shopping.kafka;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shopping.model.Entity;
import com.shopping.model.OrderEntity;

@Service
public class KafkaPublisherImpl implements KafkaPublisher {

	private static Logger logger = LoggerFactory.getLogger(KafkaPublisherImpl.class);

	private Producer<String, Entity> producer;
	private String topic;
	private int partition;

	@Autowired
	public KafkaPublisherImpl(final KafkaProducerFactory kafkaProducerFactory, @Value("${topicName}") String topic,
			@Value("0") int partition) {
		this.producer = kafkaProducerFactory.createProducer(new StringSerializer(), new EntitySerializer());
		this.topic = topic;
		this.partition = partition;
	}

	@Override
	public void publishOrder(OrderEntity orderEntity) {
		KafkaHeaders headers = new KafkaHeaders(orderEntity.getEntityType().toString(), orderEntity.getEntityId());
		headers.addHeader("ORDER_NAME", orderEntity.getName());
		publish(headers, orderEntity);
	}

	private void publish(KafkaHeaders headers, OrderEntity orderEntity) {
		logger.info("Publishing {} : {}", orderEntity.getEntityType(), orderEntity.getEntityId());
		ProducerRecord<String, Entity> record = new ProducerRecord<String, Entity>(this.topic, this.partition, null,
				orderEntity.getEntityId(), orderEntity, headers.getHeaders());
		Future<RecordMetadata> future = this.producer.send(record);
		try {
			logger.debug("Entity {} {} offset {}", orderEntity.getEntityType(), orderEntity.getId(),
					future.get(30, TimeUnit.SECONDS).offset());
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
	}

}
