package com.testing.services;

import java.util.List;

import com.testing.payloads.CommentDto;

public interface CommentService {

	public CommentDto createComment(Long postId,CommentDto commentDto);

	List<CommentDto> getCommentByPostId(long postId);

	public CommentDto updateCommentsById(long postId, long commentsId, CommentDto commentDto);

	public String deleteComemntById(long postId, long commentId);
}
