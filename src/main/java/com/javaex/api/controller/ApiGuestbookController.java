package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@ResponseBody
	@RequestMapping("/write")
	public GuestBookVo write(@RequestBody GuestBookVo gbVo) {
		System.out.println("api:write");
		
		
		GuestBookVo vo = gbService.addGuest(gbVo);
		System.out.println("보내줄vo"+vo.toString());
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(@RequestParam("no") int no,
            @RequestParam("password") String password) {
		System.out.println("api:delete");
		System.out.println(no+"입력한 비번"+password);

		int count = gbService.delete(no, password);
		
		return count;
	}
}
