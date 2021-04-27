package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Mood;
import com.nefu.mvc.entity.Tag;
import com.nefu.mvc.entity.TagMood;
import com.nefu.mvc.service.MoodService;
import com.nefu.mvc.service.TagMoodService;
import com.nefu.mvc.service.TagService;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private TagMoodService tagMoodService;

    //动态
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

    //话题
    @PostMapping("tag")
    public CommonResult saveTag(@RequestBody Tag tag){
        return new CommonResult(200,"添加话题成功",tagService.saveTag(tag));
    }

    @DeleteMapping("tag")
    public CommonResult deleteTag(@RequestParam("id")int id){
        return tagService.deleteTag(id) == 0 ?
                new CommonResult(401,"该话题不存在")
                :new CommonResult(200,"删除话题成功");
    }

    @PatchMapping("tag")
    public CommonResult updateTag(@RequestBody Tag tag){
        return tagService.updateTag(tag) == 0 ?
                new CommonResult(401,"该话题不存在")
                :new CommonResult(200,"修改话题成功");
    }

    @GetMapping("tags")
    public CommonResult getTages(){
        return new CommonResult(200,"话题列表",tagService.getTags());
    }

    @GetMapping("tag/{id}")
    public CommonResult getTagById(@PathVariable("id") int id){
        Tag tag = tagService.getTagById(id);
        return tag == null ?
                new CommonResult(401,"该话题不存在")
                :new CommonResult(200,"查找到话题",tag);
    }

    //给动态和话题的关联，包括添加一个关联、或者查询对应话题有什么动态，对应动态带上什么话题等。
    @PostMapping("tag/mood")
    public CommonResult saveTagMood(@RequestBody TagMood tagMood){
        return new CommonResult(200,"插入成功",tagMoodService.saveTagMood(tagMood));
    }

    @DeleteMapping("tag/mood")
    public CommonResult deleteTagMoodById(@RequestParam("id") int id){
        return tagMoodService.deleteTagMood(id) == 0 ?
                new CommonResult(401,"该动态没有对应话题，无法删除")
                :new CommonResult(200,"删除该动态的话题成功");
    }

    @GetMapping("mood/tag/{id}")
    public CommonResult getMoodByTag(@PathVariable("id") int id){
        return new CommonResult(200,"该话题包含的动态",tagMoodService.getMoodByTag(id));
    }

    @GetMapping("tag/mood/{id}")
    public CommonResult getTagByMood(int id){
        return new CommonResult(200,"该动态包含的话题",tagMoodService.getTagByMood(id));
    }
}
