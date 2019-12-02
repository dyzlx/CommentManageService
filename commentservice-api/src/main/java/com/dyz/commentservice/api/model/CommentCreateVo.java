package com.dyz.commentservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateVo {

	private String content;

	private String type;

	private String isSubComment;

	private Integer targetResourceId;
}
