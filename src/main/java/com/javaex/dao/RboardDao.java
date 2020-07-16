package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//게시판 목록
	public List<RboardVo> getRbList(Map<String,Object> map){
		System.out.println("다오:리플게시판 리스트");
		System.out.println(map.toString());
		List<RboardVo> print = sqlSession.selectList("rboard.rlist", map);
		System.out.println(print);
		return print;
	}
	
	//페이지 읽기
	public int page(String keyword) {
		System.out.println("다오:리플 페이지 숫자");
		System.out.println(keyword);
		return sqlSession.selectOne("rboard.rpageCount", keyword);
	}
	
	//게시글 읽기
	public RboardVo read(int no) {
		System.out.println("다오:게시물 읽기");
		return sqlSession.selectOne("rboard.read", no);
		
	}
	
	//조회수 증가
	public int hitup(int no) {
		System.out.println("다오:조회수 증가");
		return sqlSession.update("rboard.hit", no);
	}
	
	
	//글쓰기
	public int insert(RboardVo rvo) {
		System.out.println("다오:게시물 쓰기");
		System.out.println("다오"+rvo.toString());
		return sqlSession.insert("rboard.rinsert", rvo);
	}


	//글 순서 증가
	public int order(int group_no, int order_no) {
		System.out.println("다오:글 순서 증가");
		Map<String,Integer> oMap = new HashMap<>();
		oMap.put("group_no", group_no);
		oMap.put("order_no", order_no);
		
		return sqlSession.update("rboard.rodernum", oMap);
	}
}
