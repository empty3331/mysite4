package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;
	
	
	//게시판리스트
	public List<BoardVo> blist(int page,String keyword){
		System.out.println("서비스:리스트");

		Map<String,Object> listMap = new HashMap<>();
		
		listMap.put("start", 1+(page-1)*5 );
		listMap.put("end", 1+(page-1)*5+(5-1));
		listMap.put("keyword", keyword);
		
		List<BoardVo> bv = bDao.getBoardList(listMap);
		
		System.out.println(bv.toString());
		
		return bv;
	}
	
	//페이지 읽기
	public Map<String, Integer> pageCount(String keyword) {
		System.out.println("서비스:페이지 카운트");
		
		Map<String, Integer> cMap = new HashMap<>();
		cMap.put("countAll", bDao.pageCount(keyword));
		cMap.put("count", (int)Math.ceil(cMap.get("countAll")/5.0));
		
		System.out.println(cMap.toString());
		
		return cMap;
		
		
	}
	
	
	//게시물 읽기
	public BoardVo read(int no) {
		System.out.println("서비스:게시물읽기");
		
		System.out.println(no);
		bDao.hitup(no);

		return bDao.read(no);
	}
	
	//게시물쓰기
	public  int write(BoardVo bVo) {
		System.out.println("서비스:게시물쓰기");
		return bDao.insert(bVo);
	}
	
	//게시물삭제
	public int delete(BoardVo bVo) {
		System.out.println("서비스:게시물삭제");
		return bDao.delete(bVo);
	}
	
	//게시물수정
	public int update(BoardVo bVo) {
		System.out.println("서비스:게시물수정");
		return bDao.update(bVo);
	}
	
	//게시물검색
	public List<BoardVo> search(String key){
		System.out.println("서비스:검색");
		List<BoardVo> bv = bDao.search(key);
		System.out.println(bv.toString());
		return bv;
	}
	
}