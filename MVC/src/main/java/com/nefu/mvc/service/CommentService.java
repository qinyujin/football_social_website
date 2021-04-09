package com.nefu.mvc.service;

import com.nefu.mvc.entity.Comment;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-09 19:18:00
 */
public interface CommentService {
    int saveComment(Comment c);

    int deleteComment(int id);

    int updateComment(Comment c);

    List<Comment> getComments();

    Comment getCommentById(int id);
}
