package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping(value = "/api/guest")
public class ApiGuestbookController {
	
	@Autowired
	private GuestBookService gbService;
	
	@ResponseBody
	@RequestMapping(value = "/list")
	public List<GuestBookVo> list() {
		System.out.println("api :list");
		
		 List<GuestBookVo> gbVo = gbService.gbList();
		
		return gbVo;
	}
}
