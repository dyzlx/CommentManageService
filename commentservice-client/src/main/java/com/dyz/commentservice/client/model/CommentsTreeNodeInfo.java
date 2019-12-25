package com.dyz.commentservice.client.model;

import lombok.Data;

import java.util.List;

@Data
public class CommentsTreeNodeInfo extends CommentInfo{

    private List<CommentsTreeNodeInfo> childComments;
}
