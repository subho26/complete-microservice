package com.payment.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import lombok.Getter;

@Getter
public class KafkaConsumerFactory {

	private Map<String, Object> configProperties = new HashMap<>();

	private KafkaConsumerFactory(Map<String, Object> configProperties) {
		this.configProperties = configProperties;
	}

	public Consumer<String, String> createConsumer() {
		Deserializer<String> deserializer = new StringDeserializer();
		return new KafkaConsumer<>(configProperties, deserializer, deserializer);
	}

	public static ConsumerFactoryBuilder newConsumerFactoryBuilder() {
		return new ConsumerFactoryBuilder();
	}

	public static class ConsumerFactoryBuilder {

		private KafkaConfig kafkaConfig;
		private String clientId;
		private String consumerGroupId;;

		public ConsumerFactoryBuilder setKafkaConfig(KafkaConfig config) {
			kafkaConfig = config;
			return this;
		}

		public ConsumerFactoryBuilder setClientId(String clientId) {
			this.clientId = clientId;
			return this;
		}

		public ConsumerFactoryBuilder setConsumerGroupId(String consumerGroupId) {
			this.consumerGroupId = consumerGroupId;
			return this;
		}

		public KafkaConsumerFactory build() {
			Map<String, Object> configProperties = new HashMap<>();
			configProperties = kafkaConfig.getConfigProperties();
			configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
			configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
			return new KafkaConsumerFactory(configProperties);
		}
	}
}
