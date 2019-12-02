package com.dyz.commentservice.sal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dyz.commentservice.common.exception.IllegalParamException;
import com.dyz.commentservice.common.exception.NoDataException;
import com.dyz.commentservice.domain.entity.Comment;
import com.dyz.commentservice.domain.repository.CommentRepository;
import com.dyz.commentservice.sal.bo.CommentCreateBo;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentQueryBo;
import com.dyz.commentservice.sal.service.CommentService;
import com.dyz.commentservice.sal.translation.CommentModelTranslator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Override
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public List<CommentInfoBo> queryCommentInfo(@NotNull CommentQueryBo queryBo) {
		log.info("begin to query comments, query object = {}", queryBo);
		if (Objects.isNull(queryBo)) {
			log.error("queryBo object is null");
			throw new IllegalParamException(0, "query param is null");
		}
		String type = Objects.isNull(queryBo.getType()) ? null : queryBo.getType().toString();
		List<Comment> comments = commentRepository.queryCommentInfo(queryBo.getTargetResourceId(),
				queryBo.getPublisherId(), type, queryBo.getFromTime(), queryBo.getToTime());
		List<CommentInfoBo> results = CommentModelTranslator.toBoList(comments);
		log.info("end of query comments, result = {}", results);
		return results;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public Integer createComment(@NotNull CommentCreateBo createBo, @NotNull Integer userId) {
		log.info("begin to create comment, createBo = {}, userId = {}", createBo, userId);
		if (!ObjectUtils.allNotNull(createBo, createBo.getTargetResourceId(), createBo.getType(), createBo.getContent(),
				userId)) {
			log.error("create comment param is null");
			throw new IllegalParamException(0, "create param is null");
		}
		Comment newComment = Comment.builder().content(createBo.getContent()).publisherId(userId)
				.targetResourceId(createBo.getTargetResourceId()).type(createBo.getType().toString())
				.createTime(new Date()).isSubComment(createBo.isSubComment()).build();
		commentRepository.save(newComment);
		log.info("end of create comment, new comment = {}", newComment);
		return newComment.getId();
	}

	@Override
	@Transactional(rollbackFor = { Exception.class }, propagation = Propagation.REQUIRED)
	public void deleteComment(@NotNull Integer commentId, @NotNull Integer userId) {
		log.info("begin to delete comment, commentId = {}, userId = {}", commentId, userId);
		if (!ObjectUtils.allNotNull(commentId, userId)) {
			log.error("delete comment param is null");
			throw new IllegalParamException(0, "delete param is null");
		}
		Comment deleteComment = commentRepository.queryByPublisherIdAndId(userId, commentId);
		if (Objects.isNull(deleteComment)) {
			log.error("no such comment, userId = {}, commentId = {}", userId, commentId);
			throw new NoDataException(0, "no such comment");
		}
		commentRepository.delete(deleteComment);
		log.info("end of delete comment, deleted comment = {}", deleteComment);
	}
}
