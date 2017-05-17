package com.turvo.bdi.in.model;

import com.google.gson.annotations.SerializedName;

public class CustomerCommunicationDetail {
	
	private transient String contextId;
	
	@SerializedName("id")
	private String id;
	@SerializedName("email.type")
	private String emailType;
	@SerializedName("email")
	private String email;
	
	@SerializedName("phone.type")
	private String phoneType;
	@SerializedName("phone")
	private String phone;
	@SerializedName("phone.extension")
	private String phoneExtension;
	
	@SerializedName("address.type")
	private String addressType;
	@SerializedName("address.line1")
	private String addressLine1;
	@SerializedName("address.line2")
	private String addressLine2;
	@SerializedName("address.city")
	private String addressCity;
	@SerializedName("address.state")
	private String addressState;
	@SerializedName("address.zip")
	private String addressZip;
	
	@SerializedName("isPrimary")
	private Boolean isPrimary;
	
}
