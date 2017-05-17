package com.turvo.bdi.in.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.turvo.bdi.in.metadata.FilesMetaData;
import com.turvo.bdi.in.model.Entity;

@Configuration
@PropertySource("classpath:config/bdi.properties")
public class BdiConfig {

	@Value("${local.files.path}")
	private String localFilePath;

	@Value("${customer.master.file.name}")
	private String customerMasterFileName;
	@Value("${customer.comission_users.file.name}")
	private String customerComissionUsersFileName;
	@Value("${customer.communication_channel.file.name}")
	private String customerCommunicationChannelFileName;

	@Value("${connect.url}")
	private String connectUrl;
	@Value("${connect.authToken}")
	private String authToken;
	@Value("${connect.publisherId}")
	private String publisherId;
	@Value("${customer.connect.topic}")
	private String customerSubscriptionTopic;

	@Bean
	public FilesMetaData filesMetaData() {
		FilesMetaData filesMetaData = new FilesMetaData();

		filesMetaData.setLocalFilePath(localFilePath);
		Map<String, String> entityMasterFiles = new HashMap<String, String>();
		entityMasterFiles.put(Entity.CUSTOMER.get(), customerMasterFileName);
		filesMetaData.setEntityMasterFiles(entityMasterFiles);

		filesMetaData.setCustomerComissionUsersFileName(customerComissionUsersFileName);
		filesMetaData.setCustomerCommunicationChannelFileName(customerCommunicationChannelFileName);

		return filesMetaData;
	}

	@Bean
	public ConnectConfig connectConfig() {
		ConnectConfig connectConfig = new ConnectConfig();
		connectConfig.setConnectUrl(connectUrl);
		connectConfig.setAuthToken(authToken);
		connectConfig.setPublisherId(publisherId);
		Map<String, String> entityTopics = new HashMap<String, String>();
		entityTopics.put(Entity.CUSTOMER.get(), customerSubscriptionTopic);

		connectConfig.setEntityTopics(entityTopics);

		return connectConfig;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}