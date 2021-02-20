package com.shopping.kafka;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;

public class KafkaHeaders {
	private static final String MESSAGE_TYPE = "MESSAGE_TYPE";
	private static final String MESSAGE_ID = "MESSAGE_ID";
	private Headers headers;

	private KafkaHeaders() {
		headers = new RecordHeaders();
	}

	public KafkaHeaders(final String messageType, final String messageId) {
		this();
		this.headers.add(new RecordHeader(MESSAGE_TYPE, messageType.getBytes()));
		this.headers.add(new RecordHeader(MESSAGE_ID, messageId.getBytes()));
	}

	public KafkaHeaders addHeader(final String k, final String v) {
		this.headers.add(new RecordHeader(k, v.getBytes()));
		return this;
	}

	public Headers getHeaders() {
		return this.headers;
	}
}
