package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Video;
import com.nefu.mvc.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-13 19:41:00
 */
@RestController
@RequestMapping("/api/video/")
public class VideoManagerController {
    @Autowired
    private VideoService videoService;

    @PostMapping("video")
    public CommonResult saveVideo(@RequestBody Video video) {
        videoService.saveVideo(video);
        return new CommonResult(200, "添加视频成功");
    }

    @DeleteMapping("video")
    public CommonResult deleteVideo(@RequestParam("id") int id) {
        return videoService.deleteVideo(id) == 0 ?
                new CommonResult(401, "视频不存在") :
                new CommonResult(200, "删除视频成功");
    }

    @PatchMapping("video")
    public CommonResult updateVideo(@RequestBody Video video) {
        return videoService.updateVideo(video) == 0 ?
                new CommonResult(401, "更新失败") :
                new CommonResult(200, "更新成功");
    }

    //获取视频列表
    @GetMapping("videos")
    public CommonResult getVideos(){
        return new CommonResult(200,"视频列表",videoService.getVideos());
    }

    @GetMapping("video/{id}")
    public CommonResult getVideoById(@PathVariable("id") int id){
        Video u = videoService.getVideoById(id);
        return u==null ? new CommonResult(401,"该用户不存在")
                :new CommonResult(200,"查找到用户",u);
    }
}
