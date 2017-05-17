package com.turvo.bdi.in.clients;

import org.springframework.stereotype.Component;

@Component
public class FtpClient {

	private String FTP_SERVER_URL = "ftpurl";
	private String ALL_FILES_PATH = "/abc/xyz";
	
	public void download(String entityName,String localPath){
		String remotePath = FTP_SERVER_URL+ALL_FILES_PATH+entityName;
		
		// read form remote path
		
		// write to local path
	}
}
