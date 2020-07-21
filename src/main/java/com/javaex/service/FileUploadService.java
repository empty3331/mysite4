package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	
	public String restore(MultipartFile file) {
		//////////////파일카피////////
		String saveDir = "C:\\javaStudy\\upload";
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("확장자"+exName);
		
		//저장파일이름
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString() + exName; 
		System.out.println("저장이름"+saveName);		
				
		//파일경로
		String filePath = saveDir+"\\"+saveName;
		System.out.println("파일경로"+filePath);
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("파일사이즈"+fileSize);
		
		///////////////파일서버에 복사////////////////////////////
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일 --> 필요정보추출 --> DB에 저장
		//no,orgName,saveName,filePath,fileSize
		
		return saveName;
	}

}
