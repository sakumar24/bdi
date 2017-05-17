package com.turvo.bdi.in.helper.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.turvo.bdi.in.helper.CustomerHelper;
import com.turvo.bdi.in.helper.EntityHelper;
import com.turvo.bdi.in.model.Entity;

@Component
public class EntityHelperFactory {
	private Map<Entity, EntityHelper> helperMap;
	private boolean isInitialized = Boolean.FALSE;

	@Autowired
	CustomerHelper customerHelper;
	
	public EntityHelper getInstance(Entity entity){
		if(!isInitialized){
			initFactory();
		}
		return helperMap.getOrDefault(entity, null);
	}
	private void initFactory() {
		helperMap = new HashMap<>();
		
		helperMap.put(Entity.CUSTOMER, customerHelper);
		isInitialized = Boolean.TRUE;
	}
}
