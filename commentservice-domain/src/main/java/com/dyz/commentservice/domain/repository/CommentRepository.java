package com.dyz.commentservice.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dyz.commentservice.domain.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Comment queryById(Integer id);
	
	List<Comment> queryByPublisherIdAndId(Integer publisherId, Integer id);
}
