package com.dyz.commentservice.sal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.dyz.commentservice.common.model.UserContext;
import com.dyz.commentservice.common.model.UserContextHolder;
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
    public List<CommentInfoBo> queryCommentInfo(@NotNull CommentQueryBo queryBo) {
        log.info("begin to query comments, query object = {}, user context = {}", queryBo, getUserContext());
        if (Objects.isNull(queryBo)) {
            log.error("queryBo object is null");
            throw new IllegalParamException(0, "query param is null");
        }
        String type = Objects.isNull(queryBo.getType()) ? null : queryBo.getType().toString();
        if (!Objects.isNull(queryBo.getTargetResourceId()) && Objects.isNull(type)) {
            log.error("when specifying targetResourceId, no type is specified");
            throw new IllegalParamException(0, "when specifying targetResourceId, no type is specified");
        }
        List<Comment> comments = commentRepository.queryCommentInfo(queryBo.getCommentId(), queryBo.getTargetResourceId(),
                queryBo.getPublisherId(), type, queryBo.getFromTime(), queryBo.getToTime());
        List<CommentInfoBo> results = CommentModelTranslator.toBoList(comments);
        log.info("end of query comments, result = {}", results);
        return results;
    }

    @Override
    public List<CommentInfoBo> queryCommentInfoByIds(List<Integer> commentIds) {
        log.info("begin to query comments by comment ids, ids = {}", commentIds);
        if (Objects.isNull(commentIds)) {
            log.error("comment query ids is null");
            throw new IllegalParamException(0, "comment query ids is null");
        }
        List<Comment> comments = new ArrayList<>();
        commentIds.forEach(x -> {
            Comment comment = commentRepository.queryById(x);
            if (Objects.isNull(comment)) {
                log.error("no such comment, comment id = {}", x);
                throw new NoDataException(0, "no such comment id = " + x);
            }
            comments.add(comment);
        });
        List<CommentInfoBo> results = CommentModelTranslator.toBoList(comments);
        log.info("end of query comments, result = {}", results);
        return results;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public Integer createComment(CommentCreateBo createBo) {
        log.info("begin to create comment, createBo = {}, user context = {}", createBo, getUserContext());
        if (!ObjectUtils.allNotNull(createBo, createBo.getTargetResourceId(), createBo.getType(), createBo.getContent(),
                createBo.getParentId())) {
            log.error("create comment param is null");
            throw new IllegalParamException(0, "create param is null");
        }
        int parentCommentId = createBo.getParentId();
        if (!Objects.equals(parentCommentId, 0)) {
            Comment parent = commentRepository.queryById(parentCommentId);
            if (Objects.isNull(parent)) {
                log.error("no such parent comment, parent comment id = {}", parentCommentId);
                throw new NoDataException(0, "no such parent comment");
            }
        }
        Comment newComment = Comment.builder().content(createBo.getContent()).publisherId(getUserId())
                .targetResourceId(createBo.getTargetResourceId()).type(createBo.getType().toString())
                .createTime(new Date()).parentId(createBo.getParentId()).build();
        commentRepository.save(newComment);
        log.info("end of create comment, new comment = {}", newComment);
        return newComment.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void deleteComment(Integer commentId) {
        log.info("begin to delete comment, commentId = {}, user context = {}", commentId, getUserContext());
        if (!ObjectUtils.allNotNull(commentId)) {
            log.error("delete comment param is null");
            throw new IllegalParamException(0, "delete param is null");
        }
        Comment deleteComment = commentRepository.queryByPublisherIdAndId(getUserId(), commentId);
        if (Objects.isNull(deleteComment)) {
            log.error("no such comment, commentId = {}", commentId);
            throw new NoDataException(0, "no such comment");
        }
        commentRepository.delete(deleteComment);
        log.info("end of delete comment, deleted comment = {}", deleteComment);
    }

    /**
     * get user id from user context
     *
     * @return user id
     */
    public Integer getUserId() {
        return getUserContext().getUserId();
    }

    /**
     * get user context from user context holder
     *
     * @return user context
     */
    public UserContext getUserContext() {
        return UserContextHolder.getUserContext();
    }
}
