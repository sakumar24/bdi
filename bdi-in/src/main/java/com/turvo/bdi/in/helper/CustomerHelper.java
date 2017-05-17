package com.turvo.bdi.in.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.turvo.bdi.in.metadata.FilesMetaData;
import com.turvo.bdi.in.model.Customer;
import com.turvo.bdi.in.model.CustomerColumns;
import com.turvo.bdi.in.model.CustomerCommissionUser;
import com.turvo.bdi.in.model.CustomerCommunicationDetail;
import com.turvo.bdi.in.model.Entity;
import com.turvo.bdi.in.service.DataFilesService;

@Component
public class CustomerHelper implements EntityHelper{

	@Autowired
	DataFilesService dataFilesService;
	@Autowired
	FilesMetaData filesMetaData;
	@Autowired
	CustomerColumns customerColumns;

	Gson gson = new Gson();

	@Override
	public String getEntityJson(String id) {
		Customer customer = null;
		
		JSONArray customerMasterFileData = 
				dataFilesService.getDataFromMasterFile(Entity.CUSTOMER,id,
						getColumnProperties(customerColumns.getCustomerColumns(),customerColumns.getCustomerColumnsTypes()));
		if(customerMasterFileData.length() > 0){
			//FIXME : what if more than one records from master file with same id
			customer = gson.fromJson(customerMasterFileData.getJSONObject(0).toString(), Customer.class);

			JSONArray commisionUserDetailsJsonArray = 
					dataFilesService.getDataFromFile(Entity.CUSTOMER, filesMetaData.getCustomerComissionUsersFileName(),id,
							getColumnProperties(customerColumns.getCustomerCommissionColumns(),customerColumns.getCustomerCommissionColumnsTypes()));
			List<CustomerCommissionUser> customerComissionUsers = new ArrayList<>();
			for(Object commisionUserDetailsJson : commisionUserDetailsJsonArray){
				CustomerCommissionUser customerComissionUser = 
						gson.fromJson(commisionUserDetailsJson.toString(), CustomerCommissionUser.class);
				customerComissionUsers.add(customerComissionUser);
			}
			
			JSONArray communicationChannelJsonArray =
					dataFilesService.getDataFromFile(Entity.CUSTOMER, filesMetaData.getCustomerCommunicationChannelFileName(),id,
							getColumnProperties(customerColumns.getCustomerCommunicationColumns(),customerColumns.getCustomerCommunicationColumnsTypes()));
			List<CustomerCommunicationDetail> customerCommunicationDetails = 
					new ArrayList<CustomerCommunicationDetail>();
			for(Object customerCommunicationDetailJson : communicationChannelJsonArray){
				CustomerCommunicationDetail customerCommunicationDetail = 
						gson.fromJson(customerCommunicationDetailJson.toString(), CustomerCommunicationDetail.class);
				customerCommunicationDetails.add(customerCommunicationDetail);
			}

			customer.setCustomerComissionUsers(customerComissionUsers);
			customer.setCustomerCommunicationDetails(customerCommunicationDetails);
		}
		return gson.toJson(customer);
	}
	
	private Properties getColumnProperties(String column,String types){
		Properties props = new Properties();
		props.put("suppressHeaders", "true");
		props.put("headerline", column);
		props.put("columnTypes",  types);
		return props;
	}
}