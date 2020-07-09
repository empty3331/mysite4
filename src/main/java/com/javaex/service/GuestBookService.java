package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao gbDao;
	
	// 방명록 리스트
	public List<GuestBookVo> gbList(GuestBookVo gbVo) {
		System.out.println("service:guestbookList");
		
		  List<GuestBookVo> gb = gbDao.getGuestBookList();
		  
		  System.out.println(gb.toString());

		return gb;
		
	}
	
	
	// 방명록추가
	public int insert(GuestBookVo gbVo) {
		System.out.println("service:insert");
		
		int count =gbDao.guestBookInsert(gbVo);
		
		return count;
		
	}
	
	
	// 방명록 삭제
	public int delete(@RequestParam("no") int no,
			 		  @RequestParam("password") String password) {
		int count = gbDao.guestBookDelete(no, password);
		
		System.out.println(count);
		return count;
	}
	
	

}
