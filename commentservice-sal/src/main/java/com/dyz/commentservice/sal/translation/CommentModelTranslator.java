package com.dyz.commentservice.sal.translation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.dyz.commentservice.domain.entity.Comment;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentType;

public class CommentModelTranslator {

	public static CommentInfoBo toBo(Comment entity) {
		if (Objects.isNull(entity)) {
			return null;
		}
		return CommentInfoBo.builder()
				.publisherId(entity.getPublisherId())
				.targetResourceId(entity.getTargetResourceId())
				.content(entity.getContent())
				.commentId(entity.getId())
				.createTime(entity.getCreateTime())
				.type(CommentType.getType(entity.getType()))
				.isSubComment(entity.isSubComment())
				.build();
	}

	public static List<CommentInfoBo> toBoList(List<Comment> entitys) {
		if (Objects.isNull(entitys)) {
			return null;
		}
		List<CommentInfoBo> boList = new ArrayList<>();
		entitys.stream().forEach(x -> {
			boList.add(toBo(x));
		});
		return boList;
	}
}
