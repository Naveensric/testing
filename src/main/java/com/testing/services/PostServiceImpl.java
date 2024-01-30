package com.testing.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.testing.entities.Post;
import com.testing.exceptions.ResourceNotFoundException;
import com.testing.payloads.PostDto;
import com.testing.payloads.PostResponse;
import com.testing.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository post_repo;
	private ModelMapper model_mapper;
	
	public PostServiceImpl(PostRepository post_repo,ModelMapper model_mapper) {
		this.post_repo = post_repo;
		this.model_mapper = model_mapper;
	}
	

	public Post mapDtoToPostEntity(PostDto postDto) {
		Post mappedDtoToPost = model_mapper.map(postDto, Post.class);
		return mappedDtoToPost;
	}
	
	public PostDto mapPostToPostDto(Post postEntity) {
		PostDto mappedPostToDto = model_mapper.map(postEntity, PostDto.class);
		return mappedPostToDto;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		
			Post post =mapDtoToPostEntity(postDto);
			Post postEntity = post_repo.save(post);
			PostDto postdto= mapPostToPostDto(postEntity);
		return postdto;
	}
	
	@Override
	public List<PostDto> findAllPostDetails() {
		List<Post> findAllPostList = post_repo.findAll();		
		List<PostDto> convertedAllDtoList = findAllPostList.stream()
									.map( a -> mapPostToPostDto( a ))
									.collect(Collectors.toList());
		return convertedAllDtoList;
		
	}
	@Override
	public PostDto findOneDetailsById(long id) {
		String string = " where id = " + id + " and title = 'First'";
		Post oneIdDetails = post_repo.findByCheckId(string).orElseThrow(
				() -> new ResourceNotFoundException("post","id",id)
		);
		PostDto convertedOneIdDetails = mapPostToPostDto(oneIdDetails);
		return convertedOneIdDetails;
	}

	@Override
	public PostDto findAndUpdateOneDetailsById(PostDto postDto,Long id) {
		
		Post oneUserDetailsByID = post_repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("post","id",id)
		);
	
		oneUserDetailsByID.setTitle(postDto.getTitle());
		oneUserDetailsByID.setDescription(postDto.getDescription());
		oneUserDetailsByID.setContent(postDto.getContent());
		Post savedUpdatedDetails = post_repo.save(oneUserDetailsByID);
		PostDto convertedUpdatedDetails = mapPostToPostDto(savedUpdatedDetails);
		return convertedUpdatedDetails;
	}
	
	@Override
	public void findAndDeleteById(long id) {
		Post oneUserDetailsByID = post_repo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("post","id",id)
		);
		post_repo.deleteById(oneUserDetailsByID.getId());
	}


	@Override
	public PostResponse findAllPostDetailsInPage(int pageNo, int pageSize) {
		Pageable pageable= PageRequest.of(pageNo, pageSize);
		Page<Post> findAllDetailsInPage= post_repo.findAll(pageable);
		List<Post> findAllContentDetails = findAllDetailsInPage.getContent();
		List<PostDto> convertedAllDetails = findAllContentDetails.stream()
							.map( a -> mapPostToPostDto(a))
							.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(convertedAllDetails);
		postResponse.setPageNo(findAllDetailsInPage.getNumber());
		postResponse.setPageSize(findAllDetailsInPage.getSize());
		postResponse.setTotalElements(findAllDetailsInPage.getTotalElements());
		postResponse.setTotalPages(findAllDetailsInPage.getTotalPages());
		postResponse.setLastPage(findAllDetailsInPage.isLast());
		return postResponse; 
	}


	@Override
	public PostResponse findAllPostDetailsInPageSort(int pageNo, int pageSize, String sortField, String direction) {
	
	// Type 1
		Sort sortedDetails = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable1 = PageRequest.of(pageNo, pageSize, sortedDetails);
	
	// Type 2	
		Pageable pageable = PageRequest.of( pageNo, pageSize , direction.toLowerCase().equals("desc") ? Direction.DESC : Direction.ASC , sortField) ;
		
		Page<Post> findAllList = post_repo.findAll(pageable);
		List<Post> sortedPostDetails = findAllList.getContent();
				List<PostDto> sortedPostDtoDetails = sortedPostDetails.stream()
										.map( a -> mapPostToPostDto(a))
										.collect(Collectors.toList());
		PostResponse postresponse = new PostResponse();
		postresponse.setContent(sortedPostDtoDetails);
		postresponse.setPageNo(findAllList.getNumber());
		postresponse.setPageSize(findAllList.getSize());
		postresponse.setTotalElements(findAllList.getTotalElements());
		postresponse.setTotalPages(findAllList.getTotalPages());
		postresponse.setLastPage(findAllList.isLast());
				
		return postresponse;
	} 


	
	
}
