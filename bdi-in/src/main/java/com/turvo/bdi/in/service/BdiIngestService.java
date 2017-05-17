package com.turvo.bdi.in.service;

import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turvo.bdi.in.clients.ConnectClient;
import com.turvo.bdi.in.helper.EntityHelper;
import com.turvo.bdi.in.helper.factory.EntityHelperFactory;
import com.turvo.bdi.in.model.Entity;

@Service
public class BdiIngestService {
	private Log logger = LogFactory.getLog(BdiIngestService.class);

	@Autowired
	DataFilesService dataFilesService;
	@Autowired
	ConnectClient connectClient;
	@Autowired
	EntityHelperFactory entityHelperFactory;
	
	public BdiIngestService() {
		super();
	}

	public void ingestEntityData(Entity entity){
		EntityHelper entityHelper = entityHelperFactory.getInstance(entity);
		Properties props = new Properties();
		List<String> ids = dataFilesService.getIds(entity,props);
		for(String id : ids){
			String entityDetailsJson = entityHelper.getEntityJson(id);
			logger.info("Posting entity {"+entity.get()+ "} data to connect for customerId="+id);
			connectClient.sendPost(entity,entityDetailsJson);
		}
	}
}