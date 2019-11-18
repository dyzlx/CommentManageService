package com.dyz.commentservice.sal.service.impl;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyz.commentservice.domain.repository.CommentRepository;
import com.dyz.commentservice.sal.bo.CommentCreateBo;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentQueryBo;
import com.dyz.commentservice.sal.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;

	@Override
	public List<CommentInfoBo> queryCommentInfo(@NotNull CommentQueryBo queryBo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer createComment(@NotNull CommentCreateBo createBo, @NotNull Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(@NotNull Integer commentId, @NotNull Integer userId) {
		// TODO Auto-generated method stub
		
	}

}
