package com.turvo.bdi.in.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonUtils {
	private Log logger = LogFactory.getLog(JsonUtils.class);

	public JSONArray getJson(ResultSet resultSet) {
		JSONArray json = new JSONArray();
		try {
			ResultSetMetaData metadata = resultSet.getMetaData();
			int numColumns = metadata.getColumnCount();

			while (resultSet.next())
			{
				JSONObject obj = new JSONObject();
				for (int i = 1; i <= numColumns; ++i)
				{
					String column_name = metadata.getColumnName(i);
					obj.put(column_name, resultSet.getObject(column_name));
				}
				json.put(obj);
			}
		} catch (Exception e) {
			logger.error("Error while converting: "+resultSet+" to json.",e);
		}
		return json;
	}
}
