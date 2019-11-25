package com.dyz.commentservice.api.translation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.time.DateUtils;

import com.dyz.commentservice.api.model.CommentCreateVo;
import com.dyz.commentservice.api.model.CommentInfoVo;
import com.dyz.commentservice.common.constant.ServiceConstant;
import com.dyz.commentservice.common.exception.IllegalParamException;
import com.dyz.commentservice.common.util.DateHandler;
import com.dyz.commentservice.sal.bo.CommentCreateBo;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentQueryBo;
import com.dyz.commentservice.sal.bo.CommentType;

public class CommentModelTranslator {

	public static CommentQueryBo toBo(Integer targetResourceId, Integer publisherId, String type, String fromTime,
			String toTime) {
		CommentQueryBo query = null;
		try {
			query = CommentQueryBo.builder().targetResourceId(targetResourceId).publisherId(publisherId)
					.type(CommentType.getType(type))
					.fromTime(Objects.isNull(fromTime) ? null
							: DateUtils.parseDate(fromTime, ServiceConstant.DATE_FORMAT_SHORT))
					.toTime(Objects.isNull(toTime) ? null
							: DateUtils.parseDate(toTime, ServiceConstant.DATE_FORMAT_SHORT))
					.build();

		} catch (ParseException e) {
			throw new IllegalParamException(0, "illegal param");
		}
		return query;
	}

	public static CommentCreateBo toBo(CommentCreateVo vo) {
		if (Objects.isNull(vo)) {
			return null;
		}
		return CommentCreateBo.builder().content(vo.getContent()).targetResourceId(vo.getTargetResourceId())
				.type(CommentType.getType(vo.getType())).build();
	}

	public static CommentInfoVo toVo(CommentInfoBo bo) {
		if (Objects.isNull(bo)) {
			return null;
		}
		return CommentInfoVo.builder().commentId(bo.getCommentId()).content(bo.getContent())
				.createTime(DateHandler.getDateString(bo.getCreateTime())).publisherId(bo.getPublisherId())
				.targetResourceId(bo.getTargetResourceId()).type(bo.getType().toString()).build();
	}

	public static List<CommentInfoVo> toVoList(List<CommentInfoBo> boList) {
		if (Objects.isNull(boList)) {
			return null;
		}
		List<CommentInfoVo> result = new ArrayList<>();
		boList.stream().forEach(x -> {
			result.add(toVo(x));
		});
		return result;
	}
}
