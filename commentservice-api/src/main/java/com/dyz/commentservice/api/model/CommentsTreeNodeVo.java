package com.dyz.commentservice.api.model;


import lombok.Data;

import java.util.List;

@Data
public class CommentsTreeNodeVo extends CommentInfoVo {

    private List<CommentsTreeNodeVo> childComments;
}
