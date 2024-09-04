package com.application.traningVer2.practice1.postAdvance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.traningVer2.practice1.postAdvance.dao.PostDAO;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostDAO postDAO;

	@Override
	public List<Map<String, Object>> getPostList() { // 게시글 전체조회
		return postDAO.getPostList();
	}

	@Override
	public int getAllPostCnt() { // 전체 게시글 개수
		return postDAO.getAllPostCnt();
	}

}
