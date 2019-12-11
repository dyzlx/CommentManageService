package com.dyz.commentservice.api;

import com.dyz.commentservice.api.model.CommentCreateVo;
import com.dyz.commentservice.api.model.CommentInfoVo;
import com.dyz.commentservice.api.model.Result;
import com.dyz.commentservice.api.translation.CommentModelTranslator;
import com.dyz.commentservice.sal.bo.CommentQueryBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dyz.commentservice.sal.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Result> queryComment(
            @RequestParam(required = false) Integer targetResourceId,
            @RequestParam(required = false) Integer publisherId,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String fromTime,
            @RequestParam(required = false) String toTime) {
        CommentQueryBo queryBo = CommentModelTranslator.toBo(targetResourceId, publisherId, type, fromTime, toTime);
        List<CommentInfoVo> result = CommentModelTranslator.toVoList(commentService.queryCommentInfo(queryBo));
        return ResponseEntity.status(HttpStatus.OK).body(Result.builder().content(result).build());
    }

    @RequestMapping(value = "collection", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Result> queryCommentByIds(
            @RequestBody List<Integer> commentIds,
            @RequestHeader Integer userId) {
        List<CommentInfoVo> result = CommentModelTranslator.toVoList(commentService.queryCommentInfoByIds(commentIds, userId));
        return ResponseEntity.status(HttpStatus.OK).body(Result.builder().content(result).build());
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {"application/json",
            "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<Result> createComment(
            @RequestBody CommentCreateVo createVo,
            @RequestHeader Integer userId) {
        Integer id = commentService.createComment(CommentModelTranslator.toBo(createVo), userId);
        return ResponseEntity.status(HttpStatus.OK).body(Result.builder().content(id).build());
    }

    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE, produces = {"application/json",
            "application/xml"})
    public ResponseEntity<Result> deleteComment(
            @PathVariable Integer commentId,
            @RequestHeader Integer userId) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(Result.builder().build());
    }
}
