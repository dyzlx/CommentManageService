package com.dyz.commentservice.client;


import com.dyz.commentservice.client.config.FeignClientConfiguration;
import com.dyz.commentservice.client.model.CommentCreateInfo;
import com.dyz.commentservice.client.model.CommentInfo;
import com.dyz.commentservice.client.model.CommentQueryInfo;
import com.dyz.commentservice.client.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "commentservice", contextId = "commentClient", configuration = {FeignClientConfiguration.class})
public interface CommentClient {

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    Result<List<CommentInfo>> queryComment(@SpringQueryMap CommentQueryInfo queryInfo);

    @RequestMapping(value = "/comments/collection", method = RequestMethod.GET)
    Result<List<CommentInfo>> queryCommentByIds(@RequestBody List<Integer> commentIds);

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    ResponseEntity<Result> queryCommentByCommentId(@PathVariable(name = "commentId") Integer commentId);

    @RequestMapping(value = "/comments/publisher/{publisherId}", method = RequestMethod.GET)
    ResponseEntity<Result> queryCommentByPublisherId(@PathVariable(name = "publisherId") Integer publisherId);

    @RequestMapping(value = "/comments/type/{type}/target/{targetResourceId}", method = RequestMethod.GET)
    ResponseEntity<Result> queryCommentByTypeAndTargetResourceId(@PathVariable String type, @PathVariable Integer targetResourceId);

    @RequestMapping(value = "/comments", method = RequestMethod.POST, consumes = {"application/json", "application/xml"})
    Result<Integer> createComment(@RequestBody CommentCreateInfo createInfo);

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.DELETE)
    void deleteComment(@PathVariable(name = "commentId") Integer commentId);
}
