package com.turvo.bdi.in.request;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EntityInsertRequest {

	@NotNull(message = "connect url must not be null")
	private String connectUrl;
	@NotNull(message = "Auth token must not be null")
	private String authToken;
	@NotNull(message = "request nonce token must not be null")
	private String requestNonce;
	@NotNull(message = "publisher Id token must not be null")
	private String publisherId;
	@NotNull(message = "topic token must not be null")
	private String topic;
	
	@JsonCreator
	public EntityInsertRequest(@JsonProperty("connectUrl") String connectUrl, @JsonProperty("authToken") String authToken,
			@JsonProperty("requestNonce") String requestNonce, @JsonProperty("publisherId") String publisherId,
			@JsonProperty("topic") String topic) {
		super();
		this.connectUrl = connectUrl;
		this.authToken = authToken;
		this.requestNonce = requestNonce;
		this.publisherId = publisherId;
		this.topic = topic;
	}

	public String getConnectUrl() {
		return connectUrl;
	}

	public String getAuthToken() {
		return authToken;
	}

	public String getRequestNonce() {
		return requestNonce;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public String getTopic() {
		return topic;
	}
	
	
}