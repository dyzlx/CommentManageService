package com.dyz.commentservice.sal.service;


import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommentsTreeService {

    List<CommentsTreeNodeBo> getFullCommentsTree(Integer targetResourceId, String type);

    List<CommentsTreeNodeBo> generateCommentsTreeNodes(List<CommentInfoBo> allCommentsBelongToATargetResource);
}
