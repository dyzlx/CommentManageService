package com.dyz.commentservice.sal.service;


import com.dyz.commentservice.sal.bo.CommentsTreeNodeBo;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommentsTreeService {

    List<CommentsTreeNodeBo> getFullCommentsTree(@NotNull Integer targetResourceId, @NotNull String type);
}
