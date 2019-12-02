package com.dyz.commentservice.sal.bo;

import org.apache.commons.lang3.StringUtils;

import com.dyz.commentservice.common.exception.IllegalParamException;

public enum CommentType {
	file, record;

	public static CommentType getType(String type) {
		if ("file".equals(type)) {
			return CommentType.file;
		}
		if ("record".equals(type)) {
			return CommentType.record;
		}
		throw new IllegalParamException(0, "illegal comment type");
	}
}
