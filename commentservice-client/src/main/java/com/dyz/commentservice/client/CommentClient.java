package com.dyz.commentservice.client;


import com.dyz.commentservice.client.config.ClientErrorConfiguration;
import com.dyz.commentservice.client.config.ClientLogConfiguration;
import com.dyz.commentservice.client.model.CommentCreateInfo;
import com.dyz.commentservice.client.model.CommentInfo;
import com.dyz.commentservice.client.model.CommentQueryInfo;
import com.dyz.commentservice.client.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "commentservice", configuration = {ClientErrorConfiguration.class, ClientLogConfiguration.class})
public interface CommentClient {

    @RequestMapping(value = "/commentservice/comments", method = RequestMethod.GET)
    Result<List<CommentInfo>> queryComment(@SpringQueryMap CommentQueryInfo queryInfo);

    @RequestMapping(value = "/commentservice/comments/collection", method = RequestMethod.GET)
    Result<List<CommentInfo>> queryCommentByIds(
            @RequestBody List<Integer> commentIds,
            @RequestHeader(name = "userId") Integer userId);

    @RequestMapping(value = "/commentservice/comments", method = RequestMethod.POST, consumes = {"application/json", "application/xml"})
    Result<Integer> createComment(
            @RequestBody CommentCreateInfo createInfo,
            @RequestHeader(name = "userId") Integer userId);

    @RequestMapping(value = "/commentservice/comments/{commentId}", method = RequestMethod.DELETE)
    void deleteComment(
            @PathVariable(name = "commentId") Integer commentId,
            @RequestHeader(name = "userId") Integer userId);
}
