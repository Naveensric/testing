package com.testing.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.testing.entities.Comment;
import com.testing.entities.Post;
import com.testing.exceptions.ResourceNotFoundException;
import com.testing.payloads.CommentDto;
import com.testing.repositories.CommentRepository;
import com.testing.repositories.PostRepository;
@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository comment_repo;
	private PostRepository post_repo;
	private ModelMapper model_mapper;
	

	public CommentServiceImpl(CommentRepository comment_repo
							, PostRepository post_repo
							, ModelMapper model_mapper) {
		
		this.comment_repo = comment_repo;
		this.post_repo = post_repo;
		this.model_mapper = model_mapper;
	}

	public Comment mapToComment(CommentDto commentDto) {
		Comment mappedToComment = model_mapper.map(commentDto,Comment.class);
	return mappedToComment;
	}

	public CommentDto mapToCommentDto(Comment comment) {
		CommentDto mappedToCommentDto = model_mapper.map(comment,CommentDto.class);
	return mappedToCommentDto;
	}
	
	
	
	@Override
	public CommentDto createComment(Long postId,CommentDto commentDto) {
		Post postDetails = post_repo.findById(postId).orElseThrow( 
				() -> new ResourceNotFoundException("Post","id",postId)
				);
		Comment mapedToCommentForSave = mapToComment(commentDto);
		mapedToCommentForSave.setPost(postDetails);
		
		Comment savedComment = comment_repo.save(mapedToCommentForSave);
		CommentDto mapToCommentDtoForView = mapToCommentDto(savedComment);
		return mapToCommentDtoForView;
	}
	
	@Override
	public List<CommentDto> getCommentByPostId(long postId){
		List<Comment> findCommentByPostId = comment_repo.findByPostId(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post","id",postId)
				);
		List<CommentDto> convertedCommentDtoByPostId = findCommentByPostId.stream()
												.map(a-> mapToCommentDto(a))
												.collect(Collectors.toList());
		return convertedCommentDtoByPostId;
	}

	@Override
	public CommentDto updateCommentsById(long postId, long commentsId, CommentDto commentDto) {
		Post postDetailsById = post_repo.findById(postId).orElseThrow(
				() ->  new ResourceNotFoundException("Post","id",postId)
				);
		Comment commentDetailsById = comment_repo.findById(commentsId).orElseThrow(
				() ->  new ResourceNotFoundException("Comment","id",commentsId)
				);
		
		Comment convertedComments = mapToComment(commentDto);
		convertedComments.setId(commentDetailsById.getId());
		convertedComments.setPost(postDetailsById);
		Comment updatedComments = comment_repo.save(convertedComments);
		CommentDto updatedCommentDto = mapToCommentDto(updatedComments);
		return updatedCommentDto;
	}

	@Override
	public String deleteComemntById(long postId, long commentId) {
		Post postDetailsById = post_repo.findById(postId).orElseThrow(
				() ->  new ResourceNotFoundException("Post","id",postId)
				);
		Comment commentDetailsById = comment_repo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("Comment","id",commentId) 
				);
			if(commentDetailsById.getPost().getId()==postDetailsById.getId()) {
				comment_repo.deleteById(commentDetailsById.getId());
				return "Successfully Deleted Comment ID : "+commentId;
			}else {
				return "Under this Post ID : "+postId+
						" , This Comment ID : "+commentId+" Not Found";
			}
	}

}
