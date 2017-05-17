package com.turvo.bdi.in.config;

import java.util.Map;

public class ConnectConfig {

	private String connectUrl;
	private String authToken;
	private String publisherId;
	private Map<String,String> entityTopics;
	
	public String getConnectUrl() {
		return connectUrl;
	}
	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	public Map<String, String> getEntityTopics() {
		return entityTopics;
	}
	public void setEntityTopics(Map<String, String> entityTopics) {
		this.entityTopics = entityTopics;
	}
	
}