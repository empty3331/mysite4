package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;

	//갤러리 목록
	public List<GalleryVo> getGallery() {
		List<GalleryVo> gaList = sqlSession.selectList("gallery.gList");
		
		return gaList;
	}
	
	//갤러리 추가
	public int insert(GalleryVo gaVo) {
		System.out.println("다오:이미지 올리기");
		return sqlSession.insert("gallery.insert",gaVo);
	}

	//사진읽기
	public GalleryVo read(int no) {
		System.out.println("다오:이미지보기");
		
		GalleryVo vo = sqlSession.selectOne("gallery.read",no);
		System.out.println(vo);
		
		return vo;
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("다오:삭제");
		
		int delCount = sqlSession.delete("gallery.delete",no);
		
		return delCount;
	}

	


}
