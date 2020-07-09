package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 방명록 리스트
		public List<GuestBookVo> getGuestBookList() {
			List<GuestBookVo> guestBookList = sqlSession.selectList("guestbook.getList");
			return guestBookList;

		}
		
		// 방명록추가
		public int guestBookInsert(GuestBookVo guestBookVo) {
			int count = sqlSession.insert("guestbook.insert", guestBookVo);

			return count;

		}

		// 방명록 삭제
		public int guestBookDelete(int no, String password) {
			Map<String, Object> gMap = new HashMap<String, Object>();
			gMap.put("no", no);
			gMap.put("password", password);
			int count = sqlSession.delete("guestbook.delete", gMap );
			return count;
		}
	

}
