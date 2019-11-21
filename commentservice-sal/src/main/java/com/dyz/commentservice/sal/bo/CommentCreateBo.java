package com.dyz.commentservice.sal.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentCreateBo {

	private String content;
	
	private CommentType type;
	
	private Integer targetResourceId;
}
