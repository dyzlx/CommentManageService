package com.dyz.commentservice.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInfo {

    private Integer commentId;

    private String content;

    private String createTime;

    private String type;

    private Integer parentId;

    private Integer targetResourceId;

    private Integer publisherId;
}
