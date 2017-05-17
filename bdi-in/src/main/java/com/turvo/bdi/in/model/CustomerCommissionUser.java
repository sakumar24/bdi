package com.turvo.bdi.in.model;

import com.google.gson.annotations.SerializedName;

public class CustomerCommissionUser {
	
	private transient String contextId;
	
	@SerializedName("user.name")
	private String userName;
	@SerializedName("user.id")
	private String userId;
	@SerializedName("user.comission")
	private Float userCommission;

}
