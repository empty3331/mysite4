package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 목록
	public List<BoardVo> getBoardList(Map<String,Object> listMap){
		System.out.println("다오:리스트");
		System.out.println(listMap.toString());
		List<BoardVo> print = sqlSession.selectList("board.list",listMap);
		System.out.println(print);
		
		return print;
	}
	
	//페이지 읽기
	public int pageCount(String keyword) {
		System.out.println("다오:페이지 숫자");
		return sqlSession.selectOne("board.pageCount",keyword);		
	}
	
	
	
	//게시물 읽기
	public BoardVo read(int no) {
		System.out.println("다오:게시물읽기");
		return sqlSession.selectOne("board.read",no);
	}
	
	//게시물쓰기
	public int insert(BoardVo bVo) {
		System.out.println("다오:게시물쓰기");
		return sqlSession.insert("board.insert",bVo);
	}
	
	//게시물삭제
	public int delete(BoardVo bVo) {
		System.out.println("다오:게시물삭제");
		return sqlSession.delete("board.delete",bVo);
	}
	
	//게시물 수정
	public int update(BoardVo bVo) {
		System.out.println("다오:게시물수정");
		return sqlSession.update("board.update", bVo);
	}
	
	//조회수증가
	public int hitup(int no) {
		System.out.println("다오:조회수증가");
		System.out.println(no);
		return sqlSession.update("board.hitup", no);
	}
	
	//게시물 검색
	public List<BoardVo> search(String keyword) {
		System.out.println("다오:검색");
		System.out.println(keyword);
		List<BoardVo> bList = sqlSession.selectList("board.list",keyword);
		return bList;
	}

}