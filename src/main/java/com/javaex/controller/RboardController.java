package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;

@Controller
@RequestMapping("/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rService;
	
	
	@RequestMapping("/list" )
	private String list(@RequestParam("page") int page, Model model) {
		System.out.println("컨트롤러:리스트");
		List<RboardVo> rv = rService.rlist(page, "");
		Map<String,Integer> p = rService.pageCount("");
		
		model.addAttribute("rv", rv);
		model.addAttribute("p",p);
		
		return "rboard/list";
	}
	

}
