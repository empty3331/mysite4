package com.javaex.controller;

import java.util.List;
import java.util.Map;

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
	private String list(@RequestParam("page") int page, Model model) {
		System.out.println("컨트롤러:리스트");
		List<BoardVo> bv = bService.blist(page,"");
		Map<String, Integer> p = bService.pageCount("");
		
		model.addAttribute("bv", bv);
		model.addAttribute("p", p);
		
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
		System.out.println("컨트롤러:글쓰기폼");
		return "board/writeForm";
	}
	
	@RequestMapping("/write" )
	private String write(@ModelAttribute BoardVo bVo) {
		System.out.println("컨트롤러:글쓰기");
		bService.write(bVo);
		
		System.out.println(bVo.toString());
		return "redirect:/board/list?page=1";
	}
	
	@RequestMapping("/delete" )
	private String delete(@ModelAttribute BoardVo bVo) {
		System.out.println("컨트롤러:삭제");
		bService.delete(bVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/modifyForm" )
	private String modifyForm(Model model,@RequestParam("no") int no) {
		System.out.println("컨트롤러:수정폼");
		
		model.addAttribute("no",bService.read(no));
		
		System.out.println(model.toString());
		
		return "board/modifyForm";
	}

	@RequestMapping("/modify" )
	private String modify(@ModelAttribute BoardVo bVo) {
		System.out.println("컨트롤러:수정");
		
		bService.update(bVo);

		return "redirect:/board/read?no="+bVo.getNo();
	}
	
	@RequestMapping("/search" )
	private String search(@RequestParam("keyword") String keyword,@RequestParam("page") int page, Model model) {
		System.out.println("컨트롤러:검색");
		List<BoardVo> bv = bService.blist(page,keyword);
		Map<String, Integer> p = bService.pageCount(keyword);
		
		model.addAttribute("bv", bv);
		model.addAttribute("p", p);
		
		return "board/list";
		
	}
	
}