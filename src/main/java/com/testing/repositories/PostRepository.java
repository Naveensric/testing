package com.testing.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testing.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "select * from posts?1 " , nativeQuery = true)
	public Optional<Post> findByCheckId(String postID);
	

}
