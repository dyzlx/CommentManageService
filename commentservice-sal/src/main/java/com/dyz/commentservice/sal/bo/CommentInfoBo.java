package com.dyz.commentservice.sal.bo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfoBo {

	private Integer commentId;
	
	private String content;
	
	private Date createTime;

	private Integer parentId;
	
	private CommentType type;
	
	private Integer targetResourceId;
	
	private Integer publisherId;
}
