package com.dyz.commentservice.sal.service;

import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentsTreeServiceTest {

    @Autowired
    private CommentsTreeService commentsTreeService;

    @Test
    public void queryCommentsTreeTest() throws JsonProcessingException {
        List<CommentsTreeNodeBo> result = commentsTreeService.getFullCommentsTree(1, "record");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        System.out.println(json);
    }
}
