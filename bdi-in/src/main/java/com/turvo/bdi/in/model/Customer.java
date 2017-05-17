package com.turvo.bdi.in.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Customer {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name; 
	
	@SerializedName("status.code")
	private String statusCode; 
	@SerializedName("status.notes")
	private String statusNotes;
	@SerializedName("status.description")
	private String statusDescription; 
	
	@SerializedName("specialInstructions")
	private String specialInstructions;
	
	@SerializedName("billing.toName")
	private String billingToName;
	@SerializedName("billing.address.line1")
	private String billingAddressLine1;
	@SerializedName("billing.address.line2")
	private String billingAddressLine2;
	@SerializedName("billing.address.city")
	private String billingAddressCity;
	@SerializedName("billing.address.state")
	private String billingAddressState;
	@SerializedName("billing.address.zip")
	private String billingAddressZip;
	
	@SerializedName("billing.email.secondary")
	private String secondaryEmail;
	@SerializedName("billing.email.primary")
	private String primaryEmail;
	
	@SerializedName("billing.phone.number")
	private String billingPhone;
	@SerializedName("billing.phone.extension")
	private String billingPhoneExtension;
	
	@SerializedName("billing.creditLimit")
	private Float billingCreditLimit;
	
	@SerializedName("billing.payTerms")
	private String billingPayTerms;
	@SerializedName("billing.instructions")
	private String billingInstructions;
	@SerializedName("billing.contactId")
	private String billingContactId;
	@SerializedName("paysUnloading")
	private String paysUnloading;
	
	@SerializedName("owner.name")
	private String ownerName;
	@SerializedName("owner.userId")
	private String ownerId;

	private List<CustomerCommissionUser> customerComissionUsers;
	private List<CustomerCommunicationDetail> customerCommunicationDetails;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<CustomerCommissionUser> getCustomerComissionUsers() {
		return customerComissionUsers;
	}
	public void setCustomerComissionUsers(List<CustomerCommissionUser> customerComissionUsers) {
		this.customerComissionUsers = customerComissionUsers;
	}
	public List<CustomerCommunicationDetail> getCustomerCommunicationDetails() {
		return customerCommunicationDetails;
	}
	public void setCustomerCommunicationDetails(List<CustomerCommunicationDetail> customerCommunicationDetails) {
		this.customerCommunicationDetails = customerCommunicationDetails;
	}
		
}