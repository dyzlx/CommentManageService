package com.dyz.commentservice.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentInfo {

    private Integer commentId;

    private String content;

    private String createTime;

    private String type;

    private Integer parentId;

    private Integer targetResourceId;

    private Integer publisherId;
}
