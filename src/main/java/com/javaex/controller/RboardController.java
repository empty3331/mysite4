package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RboardService;
import com.javaex.vo.RboardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/rboard")
public class RboardController {
	
	@Autowired
	private RboardService rService;
	
	//글전체 리스트
	@RequestMapping("/list" )
	public String list(@RequestParam("page") int page, Model model) {
		System.out.println("컨트롤러:리스트");
		List<RboardVo> rv = rService.rlist(page, "");
		Map<String,Integer> p = rService.pageCount("");
		
		model.addAttribute("rv", rv);
		model.addAttribute("p",p);
		
		return "rboard/list";
	}
	
	//게시물 읽기
	@RequestMapping("/read" )
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("컨트롤러:글 읽기");
		
		RboardVo rVo = rService.read(no, "read");
		model.addAttribute("rVo", rVo);
		return "rboard/read";
	}
	
	//글쓰기
	@RequestMapping("/writeForm" )
	public String writeForm() {
		System.out.println("컨트롤러:글쓰기 폼");
		return "rboard/writeForm";
	}
	
	@RequestMapping("/write" )
	public String write(@ModelAttribute RboardVo rVo, HttpSession hs) {
		System.out.println("컨트롤러:글쓰기");
		
		UserVo authUser =  (UserVo) hs.getAttribute("authUser");
		rVo.setUser_no(authUser.getNo());
		rService.write(rVo, "rootWrite");
		
		System.out.println(rVo.toString());
		return "redirect:/rboard/list?page=1";

	}
	
	//글쓰기2
	@RequestMapping("/writeForm2" )
	public String writeForm2(@RequestParam("no") int no, Model model) {
		System.out.println("컨트롤러:댓글폼");
		
		RboardVo rVo = rService.read(no, "re");
		model.addAttribute("rVo", rVo);
		return "rboard/writeForm2";
	}
	
	@RequestMapping("/rwrite" )
	public String rwrite(@ModelAttribute RboardVo rVo, HttpSession hs) {
		System.out.println("컨트롤러:댓글쓰기");
		UserVo authUser =  (UserVo) hs.getAttribute("authUser");
		rVo.setUser_no(authUser.getNo());
		rService.write(rVo, "reWrite");
		
		return "redirect:/rboard/list?page=1";
	}
	
	//수정폼 이동
	@RequestMapping("/modifyForm")
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("컨트롤러:수정폼");
		
		RboardVo rVo = rService.read(no,"modify");
		model.addAttribute("no", rVo);
		
		System.out.println(rVo.toString());
		return"rboard/modifyForm";
	}
	
	//수정하기
	@RequestMapping("/modify")
	public String modify(@ModelAttribute RboardVo rVo, HttpSession hs) {
		System.out.println("컨트롤러:수정");
		UserVo authUser =  (UserVo) hs.getAttribute("authUser");
		int userNo = authUser.getNo();
		rVo.setUser_no(userNo);
		
		rService.modify(rVo);
		
		System.out.println("컨트"+rVo.toString());
		
		return"redirect:/rboard/list?page=1";
	}
}
