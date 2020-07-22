package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService gallService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("컨트롤러:갤러리 리스트");
		
		List<GalleryVo> gaVo = gallService.gaList();
		
		
		model.addAttribute("gaVo", gaVo);
		
		return "gallery/list";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file")MultipartFile file,@RequestParam("content")String content,
			             @RequestParam("user_no")int user_no,Model model) {
		System.out.println("컨트롤러:갤러리 업로드");
		
		String saveName = gallService.restore(file,content,user_no);
		model.addAttribute("saveName", saveName);
		
		System.out.println(file.getOriginalFilename());
		System.out.println(content);


		return"redirect:/gallery/list";
	}
	
	@ResponseBody
	@RequestMapping("/read")
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("컨트롤러:읽어오기");
		
		System.out.println(no);
		
		return gallService.read(no);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public int delete(int no) {
		System.out.println("컨트롤러:삭제하기");
		
		return gallService.delete(no);
		
	}

}
