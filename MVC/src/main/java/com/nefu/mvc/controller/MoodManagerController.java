package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Mood;
import com.nefu.mvc.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-13 20:30:00
 */
@RestController
@RequestMapping("/api/mood/")
public class MoodManagerController {
    @Autowired
    private MoodService moodService;

    @PostMapping("mood")
    public CommonResult saveMood(@RequestBody Mood mood) {
        return moodService.saveMood(mood) == 0 ?
                new CommonResult(401, "请勿重复添加")
                : new CommonResult(200, "添加动态成功");
    }

    @DeleteMapping("mood")
    public CommonResult deleteMood(@RequestParam("id") int id) {
        return moodService.deleteMood(id) == 0 ?
                new CommonResult(401, "动态不存在")
                : new CommonResult(200, "删除动态成功");
    }

    @PatchMapping("mood")
    public CommonResult updateMood(@RequestBody Mood mood) {
        return new CommonResult(200, "更新成功", moodService.updateMood(mood));
    }

    @GetMapping("moods")
    public CommonResult getMoods() {
        return new CommonResult(200, "动态列表", moodService.getMoods());
    }

    @GetMapping("mood/{id}")
    public CommonResult getMoodById(@PathVariable("id") int id) {
        Mood m = moodService.getMoodById(id);
        return m == null ? new CommonResult(401, "动态不存在")
                : new CommonResult(200, "查找到动态", m);
    }
}
