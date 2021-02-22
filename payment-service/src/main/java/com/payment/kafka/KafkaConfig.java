package com.payment.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class KafkaConfig {
	private String bootstrapServers;

	public Map<String, Object> getConfigProperties() {
		Map<String, Object> configProperties = new HashMap<>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServers);
		return configProperties;
	}
}