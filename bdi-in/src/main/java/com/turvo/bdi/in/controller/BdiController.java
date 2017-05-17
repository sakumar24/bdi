package com.turvo.bdi.in.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.turvo.bdi.in.model.Entity;
import com.turvo.bdi.in.service.BdiIngestService;

@Controller
@RequestMapping("/bdi")
public class BdiController {
	private Log logger = LogFactory.getLog(BdiController.class);

	@Autowired
	BdiIngestService bdiIngestService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	//@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String test(){
		System.out.println("Hello");
		return "Sucess";
	}

	@RequestMapping(value = "/{entityName}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void ingestEntityData(@PathVariable("entityName") String entityName){
		logger.info("Recieved request to ingest data for entity:"+entityName);
		if(Entity.fromValue(entityName) == Entity.INVALID){
			logger.error("Invalid entity "+entityName+" in insert data request");
			throw new IllegalArgumentException("Invalid entity "+entityName+" in insert data request");
		}
		bdiIngestService.ingestEntityData(Entity.fromValue(entityName));
	}
}