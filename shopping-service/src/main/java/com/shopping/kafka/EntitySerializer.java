package com.shopping.kafka;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.model.Entity;

public class EntitySerializer implements Serializer<Entity> {

	private ObjectMapper objectMapper;

	public EntitySerializer() {
		objectMapper = new ObjectMapper();
	}

	public EntitySerializer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public byte[] serialize(String topic, Entity entity) {
		try {
			return objectMapper.writeValueAsString(entity).getBytes();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
