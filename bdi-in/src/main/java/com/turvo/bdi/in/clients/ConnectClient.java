package com.turvo.bdi.in.clients;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.bdi.in.config.ConnectConfig;
import com.turvo.bdi.in.model.Entity;

@Component
public class ConnectClient {

	private Log logger = LogFactory.getLog(ConnectClient.class);
	@Autowired
	ConnectConfig connectConfig;
	
	public void sendPost(Entity entity, String payload) {
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(connectConfig.getConnectUrl());
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("payload",new JSONObject(payload));
			requestBody.put("topic", connectConfig.getEntityTopics().get((entity.get())));
			requestBody.put("publisherId",connectConfig.getPublisherId());

			logger.info("Sending POST request to connect with request body:"+requestBody.toString(4)
			+" on url:"+connectConfig.getConnectUrl());
			
			StringEntity postEntity = new StringEntity(requestBody.toString());
			httpPost.setEntity(postEntity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("Authorization","Bearer "+connectConfig.getAuthToken());
 
			CloseableHttpResponse response = client.execute(httpPost);
			logger.info("Response from connect :"+response.getStatusLine());
			client.close();
		} catch (Exception e) {
			logger.error("Exception while sending request to connect:",e);
		}
	}
}
