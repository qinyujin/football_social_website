package com.nefu.mvc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nefu.mvc.entity.Comment;
import com.nefu.mvc.mapper.CommentMapper;
import com.nefu.mvc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author :覃玉锦
 * @create :2021-04-12 19:25:00
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int saveComment(Comment c) {
        return commentMapper.insert(c);
    }

    @Override
    public int deleteComment(int id) {
        return commentMapper.deleteById(id);
    }

    @Override
    public int updateComment(Comment c) {
        return commentMapper.updateById(c);
    }

    @Override
    public List<Comment> getComments() {
        return commentMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Comment getCommentById(int id) {
        return commentMapper.selectById(id);
    }
}
