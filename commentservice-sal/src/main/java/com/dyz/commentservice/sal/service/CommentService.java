package com.dyz.commentservice.sal.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.dyz.commentservice.sal.bo.CommentCreateBo;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentQueryBo;

public interface CommentService {

	/**
	 * 
	 * @param queryBo
	 * @return
	 */
	List<CommentInfoBo> queryCommentInfo(@NotNull CommentQueryBo queryBo);
	
	/**
	 * 
	 * @param createBo
	 * @param userId
	 * @return
	 */
	Integer createComment(@NotNull CommentCreateBo createBo, @NotNull Integer userId);
	
	/**
	 * 
	 * @param commentId
	 * @param userId
	 */
	void deleteComment(@NotNull Integer commentId, @NotNull Integer userId);
}
