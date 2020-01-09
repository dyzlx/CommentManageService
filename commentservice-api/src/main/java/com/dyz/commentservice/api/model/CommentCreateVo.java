package com.dyz.commentservice.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateVo {

	@NotBlank
	private String content;

	@Size(min = 1, max = 20)
	@NotBlank
	private String type;

	@NotNull
	private Integer parentId;

	@NotNull
	private Integer targetResourceId;
}
