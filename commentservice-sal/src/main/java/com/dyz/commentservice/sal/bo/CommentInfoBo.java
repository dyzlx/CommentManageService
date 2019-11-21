package com.dyz.commentservice.sal.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInfoBo {

	private String content;
	
	private CommentType type;
	
	private Integer targetResourceId;
	
	private Integer publisherId;
}
