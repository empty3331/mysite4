package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
@RequestMapping("/guest")
public class GuestBookController {
	
	@Autowired
	private GuestBookService gbService;
	
	
	@RequestMapping("/addlist" )
	public String addList(Model model) {
		System.out.println("controller:addlist");
		List<GuestBookVo> gb = gbService.gbList();
		
		model.addAttribute("gb", gb);
		
		return "guestbook/addList";
	}
	
	@RequestMapping("/add" )
	public String add(@ModelAttribute GuestBookVo gbVo) {
		System.out.println("controller:add");
		
		gbService.insert(gbVo);
		
		
		return"redirect:/guest/addlist";
	}
	
	@RequestMapping("/deleteForm" )
	public String deleteForm(Model model,@RequestParam("no") int no) {
		System.out.println("deleteForm");
		
		model.addAttribute("no",no);
		
		return "guestbook/deleteForm";
	}
	
	@RequestMapping("/delete" )
	public String delete(@RequestParam("no") int no,
			             @RequestParam("password") String password) {
		
		gbService.delete(no, password);
		
		return "redirect:addlist";
		
	}	
	
	//ajax 방명록
	@RequestMapping("/ajaxList" )
	public String ajaxList() {
		System.out.println("ajaxList");
		
		return"guestbook/ajaxList";
	}
	

}
