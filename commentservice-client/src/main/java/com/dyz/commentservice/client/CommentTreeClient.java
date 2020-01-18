package com.dyz.commentservice.client;

import com.dyz.commentservice.client.config.ClientErrorConfiguration;
import com.dyz.commentservice.client.config.ClientLogConfiguration;
import com.dyz.commentservice.client.config.FeignClientConfiguration;
import com.dyz.commentservice.client.model.CommentsTreeNodeInfo;
import com.dyz.commentservice.client.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "commentservice", contextId = "commentTreeClient", configuration = {FeignClientConfiguration.class})
public interface CommentTreeClient {

    @RequestMapping(value = "/commentservice/comment_tree", method = RequestMethod.GET)
    Result<List<CommentsTreeNodeInfo>> queryCommentTree(
            @RequestParam(name = "targetResourceId") Integer targetResourceId,
            @RequestParam(name = "type") String type);
}
