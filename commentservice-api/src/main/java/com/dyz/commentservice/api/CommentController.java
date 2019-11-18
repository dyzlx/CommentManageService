package com.dyz.commentservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dyz.commentservice.sal.service.CommentService;

@RestController
@RequestMapping(value = "comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

}
