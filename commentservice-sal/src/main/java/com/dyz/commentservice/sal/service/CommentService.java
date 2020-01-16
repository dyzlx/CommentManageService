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
     * @param createBo
     * @return
     */
    Integer createComment(CommentCreateBo createBo);

    /**
     * @param commentId
     */
    void deleteComment(Integer commentId);
}
