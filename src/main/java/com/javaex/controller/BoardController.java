package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	
	@RequestMapping("/list" )
	private String list(@ModelAttribute BoardVo bVo, Model model) {
		System.out.println("컨트롤러:리스트");
		List<BoardVo> bv = bService.blist(bVo);
		model.addAttribute("bv", bv);
		
		return "board/list";
	}
	
	@RequestMapping("/read" )
	private String read(@RequestParam("no") int no,Model model) {
		System.out.println("컨트롤러:읽어오기");
		
		model.addAttribute("bb",bService.read(no));
		
		return "board/read";
		
	}
	
	@RequestMapping("/writeForm" )
	private String writeForm() {
		
		return "board/writeForm";
	}
	
	@RequestMapping("/write" )
	private String write(@ModelAttribute BoardVo bVo) {
		
		bService.write(bVo);
		
		return "redirect:/board/list";
	}
	


}
