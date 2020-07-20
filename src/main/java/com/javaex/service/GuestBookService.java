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
	public List<GuestBookVo> gbList() {
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

	
	//방명록 글 저장(ajax)
	public GuestBookVo addGuest(GuestBookVo gbVo) {
		//저장
		gbDao.insert(gbVo);
		int no = gbVo.getNo();//연구해볼것
		System.out.println("no값"+no);
		
		//저장한 데이터 가져오기
		return gbDao.seletByNo(no);
	}
	
	
	
}
