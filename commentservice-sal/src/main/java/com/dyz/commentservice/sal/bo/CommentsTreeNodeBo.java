package com.dyz.commentservice.sal.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentsTreeNodeBo {

    private CommentInfoBo comment;

    private List<CommentsTreeNodeBo> subComments;
}
