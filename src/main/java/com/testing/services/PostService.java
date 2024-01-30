package com.testing.services;

import java.util.List;

import com.testing.payloads.PostDto;
import com.testing.payloads.PostResponse;

public interface PostService {

	public PostDto createPost(PostDto postDto);
	
	public List<PostDto> findAllPostDetails();
	
	public PostDto findOneDetailsById(long id);
	
	public void findAndDeleteById(long id);

	public PostDto findAndUpdateOneDetailsById(PostDto postDto,Long id);

	public PostResponse findAllPostDetailsInPage(int pageNo, int pageSize);

	public PostResponse findAllPostDetailsInPageSort(int pageNo, int pageSize, String sortField, String direction);

}
