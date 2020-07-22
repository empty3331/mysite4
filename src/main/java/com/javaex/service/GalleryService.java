package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao gaDao;
	
	//갤러리 목록
	public List<GalleryVo> gaList() {
		System.out.println("서비스:갤러리 목록");
		
		List<GalleryVo> gaVo = gaDao.getGallery();
		
		System.out.println(gaVo.toString());
		return gaVo;
	}

	//파일추가
	public String restore(MultipartFile file,String content,int user_no) {
		/////파일카피///////
		String saveDir = "C:\\javaStudy\\upload";
		
		//유저넘버 확인
		System.out.println(user_no);
		
		//원파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("원이름"+orgName);
		
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("확장자"+exName);
		
		
		//저장파일이름
		String saveName = System.currentTimeMillis()+ UUID.randomUUID().toString()+ exName;
		System.out.println("저장이름"+saveName);
		
		
		//파일경로
		String filePath = saveDir+"\\"+saveName;
		System.out.println("파일경로"+filePath);
		
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("파일사이즈"+fileSize);
		
		
		//텍스트 확인
		System.out.println(content.toString());
		
		GalleryVo vo = new GalleryVo(user_no,content,filePath,orgName,saveName,fileSize);
		
		System.out.println(vo.toString());
				
		//////파일서버에 복사//////////////
		try {
			byte[] fileDate = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileDate);
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//파일 --> 필요정보추출 --> DB에 저장
		//no,orgName,saveName,filePath,fileSize
		
		gaDao.insert(vo);
		
		return saveName;
	}

	public GalleryVo read(int no) {
		System.out.println("서비스:게시물읽기");
		
		System.out.println(no);
		
		return gaDao.read(no);
	}

	public int delete(int no) {
		System.out.println("서비스:삭제");
		
		
		return gaDao.delete(no);
	}

	
	

}
