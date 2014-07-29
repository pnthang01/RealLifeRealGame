package com.rlrg.webserver.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MvcCommonService{
	
	@Autowired 
	private ServletContext servletContext;
	 
	private final static String URI_LINK = "http://localhost:8080/admin/resources-uri/";
	
	public String handleUploadFile(MultipartFile multipartFile) throws IllegalStateException, IOException{
		String realPath = servletContext.getRealPath("/resources-uri/");
		String fileName = String.valueOf(new Date().getTime()) + ".png";
		String pathName = new StringBuffer(realPath)
				.append("\\")
				.append(fileName).toString();
		File file = new File(pathName);
		file.createNewFile();
		//
		//multipartFile.
		multipartFile.transferTo(file);
		return fileName;
	}

}
