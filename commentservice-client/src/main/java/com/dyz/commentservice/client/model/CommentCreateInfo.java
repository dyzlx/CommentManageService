package com.dyz.commentservice.client.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateInfo {

    private String content;

    private String type;

    private Integer parentId;

    private Integer targetResourceId;
}
