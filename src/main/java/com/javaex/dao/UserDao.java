package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
		@Autowired
		private SqlSession sqlSession;
		
		//회원가입
		public int insert(UserVo userVo) {
			System.out.println("userDao:insert");
			return sqlSession.insert("user.insert",userVo);
		}
		
		//로그인
		public UserVo selectUser(UserVo userVo) {
			System.out.println("userDao:selectUser");
			return sqlSession.selectOne("user.selectUser",userVo);
		}
		
		//업데이트
		public int update(UserVo userVo) {
			System.out.println("userDao:update");
			return sqlSession.update("user.update",userVo);
		}

		//아이디체크(ajax)
		public UserVo selectUser2(String id) {
			System.out.println("userDao:selectUser");
		
			UserVo uVo = sqlSession.selectOne("user.selectById",id);
			
			return uVo;
		}
		
		
}
