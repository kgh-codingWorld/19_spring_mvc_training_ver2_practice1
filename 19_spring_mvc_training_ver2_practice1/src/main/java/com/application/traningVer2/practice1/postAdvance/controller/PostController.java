package com.application.traningVer2.practice1.postAdvance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.traningVer2.practice1.postAdvance.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("/postList") // localhost/post/postList
	public String postList(Model model) {
		
		model.addAttribute("allPostCnt", postService.getAllPostCnt());
		model.addAttribute("postListMap", postService.getPostList());
		
		return "postAdvance/post/postList"; // 포워딩
	}
	
	
}
