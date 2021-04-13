package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Comment;
import com.nefu.mvc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-13 20:06:00
 */
@RestController
@RequestMapping("/api/comment/")
public class CommentManagerController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comment")
    public CommonResult saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment) == 0 ?
                new CommonResult(401, "用户已存在，请勿重复插入")
                : new CommonResult(200, "插入成功");
    }

    @DeleteMapping("comment")
    public CommonResult deleteComment(@RequestParam("id") int id) {
        return commentService.deleteComment(id) == 0 ?
                new CommonResult(401, "删除失败")
                : new CommonResult(200, "删除成功");
    }

    @PatchMapping("comment")
    public CommonResult updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        return new CommonResult(200, "更新成功");
    }

    @GetMapping("comments")
    public CommonResult getComments() {
        return new CommonResult(200, "评论列表", commentService.getComments());
    }

    @GetMapping("comment/{id}")
    public CommonResult getCommentById(@PathVariable("id") int id) {
        Comment c = commentService.getCommentById(id);
        return c == null ? new CommonResult(401, "该评论不存在")
                : new CommonResult(200, "查找到评论", c);
    }
}
