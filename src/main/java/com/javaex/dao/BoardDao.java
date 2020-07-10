package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 목록
	public List<BoardVo> getBoardList(){
		System.out.println("다오:리스트");
		List<BoardVo> bList = sqlSession.selectList("board.list");
		
		return bList;
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
		System.out.println("다오"+bVo.toString());
		return sqlSession.delete("board.delete",bVo);
	}
	
	

}
