package com.dyz.commentservice.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentQueryInfo {

    private Integer commentId;

    private Integer targetResourceId;

    private Integer publisherId;

    private String type;

    private String fromTime;

    private String toTime;
}
