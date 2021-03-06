package com.dyz.commentservice.sal.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsTreeNodeBo extends CommentInfoBo {

    private List<CommentsTreeNodeBo> childComments;

    public static CommentsTreeNodeBo init(CommentInfoBo info, List<CommentsTreeNodeBo> childComments) {
        CommentsTreeNodeBo commentsTreeNodeBo = new CommentsTreeNodeBo();
        commentsTreeNodeBo.setChildComments(childComments);
        commentsTreeNodeBo.setCommentId(info.getCommentId());
        commentsTreeNodeBo.setContent(info.getContent());
        commentsTreeNodeBo.setCreateTime(info.getCreateTime());
        commentsTreeNodeBo.setParentId(info.getParentId());
        commentsTreeNodeBo.setPublisherId(info.getPublisherId());
        commentsTreeNodeBo.setTargetResourceId(info.getTargetResourceId());
        commentsTreeNodeBo.setType(info.getType());
        return commentsTreeNodeBo;
    }
}
