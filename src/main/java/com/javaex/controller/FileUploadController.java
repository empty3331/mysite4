package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {
	
	@Autowired
	private FileUploadService fuService;
	
	@RequestMapping("/form")
	public String form() {
		System.out.println("컨트롤러:파일업로드 폼");
		
		return "fileupload/form";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,Model model) {
		System.out.println("컨트롤러:파일업로드");
		System.out.println(file.getOriginalFilename());
		
		 String saveName= fuService.restore(file);
		 model.addAttribute("saveName", saveName);
		
		return"fileupload/result";
	}
}
