package com.application.traningVer2.practice1.postAdvance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostDAO { // mapper.xml과 연결

	public List<Map<String,Object>> getPostList(); 	// 게시글 전체조회
	public int getAllPostCnt();						// 전체게시글 개수
}
