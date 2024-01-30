package com.testing.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.testing.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query(value="select * from comments where post_id=:postId",nativeQuery=true)  
	public Optional<List<Comment>> findByPostId( @Param("postId")long postId);
	
	
}
