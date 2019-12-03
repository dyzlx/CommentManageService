package com.dyz.commentservice.sal.bo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentInfoBo {

	private Integer commentId;
	
	private String content;
	
	private Date createTime;

	private Integer parentId;
	
	private CommentType type;
	
	private Integer targetResourceId;
	
	private Integer publisherId;
}
