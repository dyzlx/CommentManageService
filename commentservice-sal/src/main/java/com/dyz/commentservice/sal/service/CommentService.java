package com.dyz.commentservice.sal.service;

import java.util.List;

import com.dyz.commentservice.sal.bo.CommentCreateBo;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentQueryBo;

public interface CommentService {

    /**
     * @param queryBo
     * @return
     */
    List<CommentInfoBo> queryCommentInfo(CommentQueryBo queryBo);

    /**
     * @param commentIds
     * @return
     */
    List<CommentInfoBo> queryCommentInfoByIds(List<Integer> commentIds);

    /**
     *
     * @param commentId
     * @return
     */
    CommentInfoBo queryCommentInfoById(Integer commentId);

    /**
     *
     * @param targetResourceId
     * @param type
     * @return
     */
    List<CommentInfoBo> queryCommentInfoByTargetResourceIdAndType(Integer targetResourceId, String type);

    /**
     *
     * @param publisherId
     * @return
     */
    List<CommentInfoBo> queryCommentInfoByPublisherId(Integer publisherId);

    /**
     * @param createBo
     * @return
     */
    Integer createComment(CommentCreateBo createBo);

    /**
     * @param commentId
     */
    void deleteComment(Integer commentId);
}
