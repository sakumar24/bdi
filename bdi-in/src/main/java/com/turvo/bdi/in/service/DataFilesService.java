package com.turvo.bdi.in.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.turvo.bdi.in.clients.FtpClient;
import com.turvo.bdi.in.metadata.FilesMetaData;
import com.turvo.bdi.in.model.Entity;
import com.turvo.bdi.in.utils.JsonUtils;

@Service
public class DataFilesService {

	private Log logger = LogFactory.getLog(DataFilesService.class);

	@Autowired
	FtpClient ftpClient;
	@Autowired
	JsonUtils jsonUtils;
	@Autowired
	FilesMetaData filesMetaData;

	private Connection getConnection(Entity entity,Properties props){
		Connection conn = null;
		try {
			Class.forName("org.relique.jdbc.csv.CsvDriver");
			// Assuming directory name for the entity is same as the entity name in code
			conn = DriverManager.getConnection("jdbc:relique:csv:"+filesMetaData.getLocalFilePath()+entity.get(),props);
		}catch (Exception e) {
			logger.error("Error while creating jdbc connection to folder:"+filesMetaData.getLocalFilePath()+entity.get(),e);
		}
		return conn;
	}

	public List<String> getIds(Entity entity,Properties props){
		List<String> ids = new ArrayList<String>();
		try {
			Statement stmt = getConnection(entity,props).createStatement();
			ResultSet results = stmt.executeQuery("SELECT id FROM "+filesMetaData.getEntityMasterFileName(entity.get()));
			while (results.next()){
				String id = results.getString(1);
				ids.add(id);
			}
		} catch (Exception e) {
			logger.error("Error while reading ids from master file:"+filesMetaData.getEntityMasterFileName(entity.get()),e);
		}
		return ids;
	}
	
	public JSONArray getDataFromMasterFile(Entity entity,String id,Properties props){
		JSONArray jsonData = new JSONArray();
		String fileName = filesMetaData.getEntityMasterFileName(entity.get());
		try {
			Statement stmt = getConnection(entity,props).createStatement();
			ResultSet resultSet = 
					stmt.executeQuery("SELECT * FROM "+fileName+" where id = "+id);
			jsonData = jsonUtils.getJson(resultSet);

		} catch (Exception e) {
			logger.error("Exception while getting data from file:"+fileName+" for Id:"+id,e);
		}
		return jsonData;
	}
	
	public JSONArray getDataFromFile(Entity entity,String fileName,String contextId,Properties props){
		JSONArray jsonData = new JSONArray();
		try {
			Statement stmt = getConnection(entity,props).createStatement();
			ResultSet resultSet = 
					stmt.executeQuery("SELECT * FROM "+fileName+" where contextId = "+contextId);
			jsonData = jsonUtils.getJson(resultSet);

		} catch (Exception e) {
			logger.error("Exception while getting data from file:"+fileName+" for context Id:"+contextId,e);
		}
		return jsonData;
	}
}