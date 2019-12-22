package com.dyz.commentservice.api.translation;

import com.dyz.commentservice.api.model.CommentsTreeNodeVo;
import com.dyz.commentservice.common.util.DateHandler;
import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentTreeModelTranslator {

    public static CommentsTreeNodeVo toVo(CommentsTreeNodeBo treeNodeBo) {
        if (Objects.isNull(treeNodeBo)) {
            return null;
        }
        CommentsTreeNodeVo result = new CommentsTreeNodeVo();
        result.setCommentId(treeNodeBo.getCommentId());
        result.setContent(treeNodeBo.getContent());
        result.setCreateTime(DateHandler.getDateString(treeNodeBo.getCreateTime()));
        result.setParentId(treeNodeBo.getParentId());
        result.setPublisherId(treeNodeBo.getPublisherId());
        result.setTargetResourceId(treeNodeBo.getTargetResourceId());
        result.setType(treeNodeBo.getType().toString());
        result.setChildComments(toVoList(treeNodeBo.getChildComments()));
        return result;
    }

    public static List<CommentsTreeNodeVo> toVoList(List<CommentsTreeNodeBo> treeNodeBoList) {
        if (Objects.isNull(treeNodeBoList)) {
            return null;
        }
        List<CommentsTreeNodeVo> results = new ArrayList<>();
        treeNodeBoList.forEach(x -> results.add(toVo(x)));
        return results;
    }
}
