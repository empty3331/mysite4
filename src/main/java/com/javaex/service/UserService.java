package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("userService:join");
		
		userDao.insert(userVo);
		
		return 1;	
	}
	
	//로그인
	public UserVo login(UserVo userVo) {
		System.out.println("userService:login");
		return userDao.selectUser(userVo);
	}
	
	//정보수정
	public int update(UserVo userVo) {
		System.out.println("userService:modify");
		return userDao.update(userVo);
		
	}

	
	
	

}
