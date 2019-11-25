package com.dyz.commentservice.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInfoVo {

	private Integer commentId;

	private String content;

	private String createTime;

	private String type;

	private Integer targetResourceId;

	private Integer publisherId;

}
