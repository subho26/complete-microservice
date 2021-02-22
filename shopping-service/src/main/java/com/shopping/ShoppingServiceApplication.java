package com.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.shopping.kafka.KafkaConfig;
import com.shopping.kafka.KafkaConfigImpl;
import com.shopping.kafka.KafkaProducerFactory;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingServiceApplication {

	private static final String BOOTSTRAP_SERVERS = "localhost:9092";

	public static void main(String[] args) {
		SpringApplication.run(ShoppingServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced // Test load balance by starting multiple instance of payment-service
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public KafkaProducerFactory getKafkaProducerFactory(KafkaConfig config, Environment env) {
		KafkaProducerFactory factory = KafkaProducerFactory.newProducerFactoryBuilder()
				.setKafkaConfig(config)
				.setProducerClientId(env.getRequiredProperty("kafka.clientId", String.class) + "--producer")
				.build();
		return factory;
	}

	@Bean
	public KafkaConfigImpl getKafkaConfigImpl(Environment environment) {
		return new KafkaConfigImpl(BOOTSTRAP_SERVERS);
	}
}
