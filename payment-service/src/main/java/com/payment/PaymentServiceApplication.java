package com.payment;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;


@SpringBootApplication
@EnableEurekaClient
@EnableKafka
public class PaymentServiceApplication {

	private static final String BOOTSTRAP_SERVERS = "localhost:9092";


	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

	/*
	 * @Bean public KafkaConsumerFactory getKafkaProducerFactory(KafkaConfig config,
	 * Environment env) { KafkaConsumerFactory factory =
	 * KafkaConsumerFactory.newConsumerFactoryBuilder().setKafkaConfig(config)
	 * .setClientId(env.getRequiredProperty("kafka.clientId", String.class) +
	 * "--consumer")
	 * .setConsumerGroupId(env.getRequiredProperty("kafka.consumerGroupId",
	 * String.class)).build(); return factory; }
	 */

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configProperties = new HashMap<>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
		configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "payment-system-conumer");
		return new DefaultKafkaConsumerFactory<>(configProperties);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		containerFactory.setConsumerFactory(consumerFactory());
		return containerFactory;
	}

	/*
	 * @Bean ConcurrentKafkaListenerContainerFactory<String, String>
	 * orderListenerContainerFactory(KafkaConfig config, Environment env){
	 * ConcurrentKafkaListenerContainerFactory<String, String> factory = new
	 * ConcurrentKafkaListenerContainerFactory<String, String>();
	 * factory.setConsumerFactory((ConsumerFactory<? super String, ? super String>)
	 * getKafkaProducerFactory(config, env)); factory.setConsumerFactory(new
	 * ConsumerFactory<K, V>); return factory; }
	 */

	/*
	 * @Bean public KafkaConfigImpl getKafkaConfigImpl(Environment environment) {
	 * return new KafkaConfigImpl(BOOTSTRAP_SERVERS); }
	 */

	/*
	 * @KafkaListener(groupId = "payment-system-conumer", topics = "order-topic",
	 * containerFactory = "orderListenerContainerFactory") public String
	 * consumeMessage(String data) { messages.add(data);
	 * System.out.println("Message received:: " + data); return data; }
	 */
}
