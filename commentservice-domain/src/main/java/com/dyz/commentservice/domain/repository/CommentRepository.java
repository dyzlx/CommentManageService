package com.dyz.commentservice.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dyz.commentservice.domain.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	Comment queryById(Integer id);
	
    Comment queryByPublisherIdAndId(Integer publisherId, Integer id);
	
	@Query(value = "select * from record where if(?1 is NULL,1=1,target_resource_id=?1)"
			+ " and if(?2 is NULL,1=1,publisher_id=?2)"
			+ " and if(?3 is NULL,1=1,type=?3)"
			+ " and create_time between ?4 and ?5", nativeQuery = true)
	List<Comment> queryCommentInfo(Integer targetResourceId, Integer publisherId, String type, Date fromTime, Date toTime);
}
