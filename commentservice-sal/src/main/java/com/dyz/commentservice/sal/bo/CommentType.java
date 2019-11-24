package com.dyz.commentservice.sal.bo;

import com.dyz.commentservice.common.exception.IllegalParamException;

public enum CommentType {
	file, record, comment;

	public static CommentType getType(String type) {
		if ("file".equals(type)) {
			return CommentType.file;
		}
		if ("record".equals(type)) {
			return CommentType.record;
		}
		if ("comment".equals(type)) {
			return CommentType.comment;
		}
		throw new IllegalParamException(0, "illegal comment type");
	}
}
