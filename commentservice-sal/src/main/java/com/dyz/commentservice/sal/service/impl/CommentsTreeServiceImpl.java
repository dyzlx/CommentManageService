package com.dyz.commentservice.sal.service.impl;

import com.dyz.commentservice.common.exception.IllegalParamException;
import com.dyz.commentservice.domain.repository.CommentRepository;
import com.dyz.commentservice.sal.bo.CommentInfoBo;
import com.dyz.commentservice.sal.bo.CommentType;
import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;
import com.dyz.commentservice.sal.service.CommentsTreeService;
import com.dyz.commentservice.sal.translation.CommentModelTranslator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dyz.commentservice.common.model.UserContextHolder.getUserContext;

@Slf4j
@Service
public class CommentsTreeServiceImpl implements CommentsTreeService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentsTreeNodeBo> getFullCommentsTree(Integer targetResourceId, String type) {
        log.info("begin to get comments tree, targetResourceId = {}, type = {}, user context = {}", targetResourceId, type, getUserContext());
        List<CommentsTreeNodeBo> result = new ArrayList<>();
        CommentType commentType = CommentType.getType(type);
        if (!ObjectUtils.allNotNull(targetResourceId, commentType)) {
            throw new IllegalParamException(0, "param is null");
        }
        List<CommentInfoBo> allComments = CommentModelTranslator.toBoList(
                commentRepository.queryByTargetResourceIdAndType(targetResourceId, commentType.toString()));
        if (!CollectionUtils.isEmpty(allComments)) {
            List<CommentInfoBo> directSubComments = allComments.stream()
                    .filter(x -> x.getParentId() == 0).collect(Collectors.toList());
            directSubComments.forEach(x -> {
                CommentsTreeNodeBo treeNode = generateCommentsTreeNode(x, allComments);
                result.add(treeNode);
            });
        }
        log.info("end of get comments tree");
        return result;
    }

    private CommentsTreeNodeBo generateCommentsTreeNode(CommentInfoBo rootComment, List<CommentInfoBo> allComments) {
        CommentsTreeNodeBo node = CommentsTreeNodeBo.init(rootComment, new ArrayList<>());
        List<CommentInfoBo> directSubNode = allComments.stream()
                .filter(x -> Objects.equals(rootComment.getCommentId(), x.getParentId()))
                .collect(Collectors.toList());
        for (CommentInfoBo subComment : directSubNode) {
            node.getChildComments().add(generateCommentsTreeNode(subComment, allComments));
        }
        return node;
    }
}
