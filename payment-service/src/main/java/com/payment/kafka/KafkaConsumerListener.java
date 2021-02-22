package com.payment.kafka;

import java.time.Duration;
import java.util.Collections;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*@Service*/
public class KafkaConsumerListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerListener.class);

	public KafkaConsumerListener() {
	}

	private KafkaConsumerFactory consumerFactory;
	private TopicPartition topicPartition;
	private Consumer<String, String> consumer;

	/*
	 * @Autowired public KafkaConsumerListener(KafkaConsumerFactory
	 * consumerFactory, @Value("${topicName}") String topicName,
	 * 
	 * @Value("0") int partition) { this.consumerFactory = consumerFactory;
	 * topicPartition = new TopicPartition(topicName, partition); }
	 */

	public void startPolling() {
		LOGGER.info("KafkaConsumerListener::startPolling");
		new Thread(this::startKafkaPolling).start();
	}

	private void startKafkaPolling() {
		LOGGER.info("Starting Polling");
		consumer = this.consumerFactory.createConsumer();
		consumer.assign(Collections.singleton(this.topicPartition));

		ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
		if (consumerRecords != null) {
			consumerRecords.forEach(consumerRecord -> {
				String record = consumerRecord.value();
				System.out.println("Message Consumed " + record);
				consumer.commitSync();
			});
		}
	}

}
