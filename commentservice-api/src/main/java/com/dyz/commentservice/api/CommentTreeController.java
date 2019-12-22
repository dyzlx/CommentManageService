package com.dyz.commentservice.api;

import com.dyz.commentservice.api.model.Result;
import com.dyz.commentservice.api.translation.CommentTreeModelTranslator;
import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;
import com.dyz.commentservice.sal.service.CommentsTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "comment_tree")
public class CommentTreeController {

    @Autowired
    private CommentsTreeService commentsTreeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Result> queryCommentTree(
            @RequestParam Integer targetResourceId,
            @RequestParam String type) {
        List<CommentsTreeNodeBo> result = commentsTreeService.getFullCommentsTree(targetResourceId, type);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Result.builder().content(CommentTreeModelTranslator.toVoList(result)).build());
    }
}
