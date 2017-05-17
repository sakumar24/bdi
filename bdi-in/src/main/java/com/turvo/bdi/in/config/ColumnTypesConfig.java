package com.turvo.bdi.in.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.turvo.bdi.in.model.CustomerColumns;

@Configuration
@PropertySource("classpath:config/column_types.properties")
public class ColumnTypesConfig {

	@Value("${customer.columns}")
	private String customerColumnsNames;
	@Value("${customer.columns.types}")
	private String customerColumnsTypes;
	
	@Value("${customer.commission.user.columns.types}")
	private String customerCommissionColumnsTypes;
	@Value("${customer.commission.user.columns}")
	private String customerCommissionColumns;
	
	@Value("${customer.communication.channel.columns.types}")
	private String customercommunicationColumnsTypes;
	@Value("${customer.communication.channel.columns}")
	private String customercommunicationColumns;
	
	@Bean
	public CustomerColumns customerColumns(){
		CustomerColumns customerColumns = new CustomerColumns();
		
		customerColumns.setCustomerColumns(customerColumnsNames);
		customerColumns.setCustomerColumnsTypes(customerColumnsTypes);
		customerColumns.setCustomerCommissionColumns(customerCommissionColumns);
		customerColumns.setCustomerCommissionColumnsTypes(customerCommissionColumnsTypes);
		customerColumns.setCustomerCommunicationColumns(customercommunicationColumns);
		customerColumns.setCustomerCommunicationColumnsTypes(customercommunicationColumnsTypes);
		
		return customerColumns;
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}