package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;


@Service
public class RboardService {
	
	@Autowired
	private RboardDao rDao;
	
	//게시판 리스트
	public List<RboardVo> rlist(int page,String keyword){
		System.out.println("서비스:리플 리스트");
		
		Map<String,Object> rMap = new HashMap<>();
		
		rMap.put("start", 1+(page-1)*5);
		rMap.put("end",1+(page-1)*5+(5-1));
		rMap.put("keyword",keyword);
		
		List<RboardVo> rv = rDao.getRbList(rMap);
		
		System.out.println(rv.toString());
		
		return rv;
	}
	
	//페이지 읽기
	public Map<String,Integer> pageCount(String keyword){
		System.out.println("서비스:페이지 카운트");
		
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("countAll", rDao.page(keyword));
		rMap.put("count", (int) Math.ceil(rMap.get("countAll")/5.0));
		
		System.out.println(rMap.toString());
		
		return rMap;
	}
	
	
	
	

}
