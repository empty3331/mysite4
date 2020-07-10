package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao bDao;
	
	
	//게시판리스트
	public List<BoardVo> blist(BoardVo bVo){
		System.out.println("서비스:리스트");
		
		List<BoardVo> bv = bDao.getBoardList();
		
		System.out.println(bv.toString());
		
		return bv;
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
	
	
	
}
