package com.turvo.bdi.in.metadata;

import java.util.Map;

public class FilesMetaData {

	private String localFilePath;
	private Map<String,String> entityMasterFiles;
	private String customerComissionUsersFileName;
	private String customerCommunicationChannelFileName;

	public FilesMetaData() {

	}
	
	public String getEntityMasterFileName(String entity) {
		return entityMasterFiles.getOrDefault(entity, null);
	}
	public void setEntityMasterFiles(Map<String, String> entityMasterFiles) {
		this.entityMasterFiles = entityMasterFiles;
	}

	public String getCustomerComissionUsersFileName() {
		return customerComissionUsersFileName;
	}

	public void setCustomerComissionUsersFileName(String customerComissionUsersFileName) {
		this.customerComissionUsersFileName = customerComissionUsersFileName;
	}

	public String getCustomerCommunicationChannelFileName() {
		return customerCommunicationChannelFileName;
	}

	public void setCustomerCommunicationChannelFileName(String customerCommunicationChannelFileName) {
		this.customerCommunicationChannelFileName = customerCommunicationChannelFileName;
	}

	public Map<String, String> getEntityMasterFiles() {
		return entityMasterFiles;
	}

	public String getLocalFilePath() {
		return localFilePath;
	}

	public void setLocalFilePath(String localFilePath) {
		this.localFilePath = localFilePath;
	}
	
	
}