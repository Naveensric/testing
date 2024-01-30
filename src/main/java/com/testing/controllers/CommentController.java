package com.testing.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testing.payloads.CommentDto;
import com.testing.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

	private CommentService comment_serv;

	public CommentController(CommentService comment_serv) {
		this.comment_serv = comment_serv;
	}

//localhost:8080/Test/api/comments/posts/1/comments
	@PreAuthorize(value = "hasRole=(ADMIN)")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<Object> createComemnt( @PathVariable("postId")Long postId
												,@Valid @RequestBody CommentDto commentDto
												,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		CommentDto createComment = comment_serv.createComment(postId, commentDto);
	return new ResponseEntity<>(createComment,HttpStatus.CREATED);
	}
	
	
//localhost:8080/Test/api/comments/postId/{postId}/allcomments	
	@GetMapping("/postId/{postId}/allcomments")
	public ResponseEntity<List<CommentDto>> getAllCommentBypostId(@PathVariable("postId")long postId){
		List<CommentDto> commentByPostId = comment_serv.getCommentByPostId(postId);
		return new ResponseEntity<List<CommentDto>>(commentByPostId,HttpStatus.FOUND);	
	}
	
	
//localhost:8080/Test/api/comments/postId/{postId}/commentsId/{commentsId}/update
	@PutMapping("/postId/{postId}/commentsId/{commentsId}/update")
	public ResponseEntity<Object> updateCommentsByID(@PathVariable("postId")long postId,
														@PathVariable("commentsId")long commentsId,
														@Valid @RequestBody CommentDto commentDto
														,BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
		}
		CommentDto updatedCommentById=comment_serv.updateCommentsById(postId,commentsId,commentDto);
		return new ResponseEntity<>(updatedCommentById,HttpStatus.OK);
	}
	
	@DeleteMapping("/postId/{postId}/commentsId/{commentId}/delete")
	public ResponseEntity<String> deleteCommentByID(@PathVariable("postId")long postId,
													@PathVariable("commentId")long commentId){
		
		String deleteComemntById = comment_serv.deleteComemntById(postId,commentId);
		
		return new ResponseEntity<>(deleteComemntById,HttpStatus.OK);
	}
	
}
