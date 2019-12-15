package com.dyz.commentservice.sal.bo;

import com.dyz.commentservice.common.exception.IllegalParamException;

public enum CommentType {
    FILE, RECORD;

	public static CommentType getType(String type) {
		if ("file".equals(type.toLowerCase())) {
			return CommentType.FILE;
		}
		if ("record".equals(type.toLowerCase())) {
			return CommentType.RECORD;
		}
		throw new IllegalParamException(0, "illegal comment type");
	}
}
