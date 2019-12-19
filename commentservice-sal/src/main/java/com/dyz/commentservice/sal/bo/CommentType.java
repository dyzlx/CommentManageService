package com.dyz.commentservice.sal.bo;

import com.dyz.commentservice.common.exception.IllegalParamException;

import java.util.Objects;

public enum CommentType {
    FILE, RECORD;

    public static CommentType getType(String type) {
        if (Objects.isNull(type)) {
            return null;
        }
        for (CommentType ct : CommentType.values()) {
            if (ct.toString().equals(type.toUpperCase())) {
                return ct;
            }
        }
        throw new IllegalParamException(0, "string " + type + " can not convent to CommentType");
    }
}
