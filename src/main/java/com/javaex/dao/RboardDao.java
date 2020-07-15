package com.javaex.dao;

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


}
