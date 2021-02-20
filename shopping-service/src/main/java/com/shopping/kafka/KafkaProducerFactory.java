package com.shopping.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;

import lombok.Getter;

@Getter
public class KafkaProducerFactory {

	private Map<String, Object> configProperties = new HashMap<>();

	private KafkaProducerFactory(Map<String, Object> configProperties) {
		this.configProperties = configProperties;
	}

	public <T> Producer<String, T> createProducer(Serializer<String> keySerializer, Serializer<T> valueSerializer) {
		return new KafkaProducer<>(configProperties, keySerializer, valueSerializer);
	}

	public static ProducerFactoryBuilder newProducerFactoryBuilder() {
		return new ProducerFactoryBuilder();
	}

	public static class ProducerFactoryBuilder {

		private KafkaConfig kafkaConfig;
		private String producerClientId;

		public ProducerFactoryBuilder setKafkaConfig(KafkaConfig config) {
			kafkaConfig = config;
			return this;
		}

		public ProducerFactoryBuilder setProducerClientId(String producerClientId) {
			this.producerClientId = producerClientId;
			return this;
		}

		public KafkaProducerFactory build() {
			Map<String, Object> configProperties = new HashMap<>();
			configProperties = kafkaConfig.getConfigProperties();
			configProperties.put(ProducerConfig.CLIENT_ID_CONFIG, producerClientId);
			return new KafkaProducerFactory(configProperties);
		}
	}
}
