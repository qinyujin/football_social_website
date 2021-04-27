package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.Schedule;
import com.nefu.mvc.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-15 16:04:00
 */
@RestController
@RequestMapping("/api/schedule/")
public class ScheduleManagerController {
    @Autowired
    private ScheduleService scheduleService;

    //添加赛程，只有管理员可以添加，普通用户和游客不具备该权限
    @PostMapping("schedule")
    public CommonResult saveSchedule(@RequestBody Schedule schedule) {
        return scheduleService.saveSchedule(schedule) == 0 ?
                new CommonResult(401, "请勿重复添加赛程")
                : new CommonResult(200, "添加赛程成功");
    }

    @DeleteMapping("schedule")
    public CommonResult deleteSchedule(@RequestParam("id") int id) {
        return scheduleService.deleteSchedule(id) == 0 ?
                new CommonResult(401, "该赛程不存在")
                : new CommonResult(200, "删除赛程成功");
    }

    @PatchMapping("schedule")
    public CommonResult updateSchedule(@RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(schedule) == 0 ?
                new CommonResult(401, "该赛程不存在")
                : new CommonResult(200, "修改成功");
    }

    @GetMapping("schedules")
    public CommonResult getSchedules() {
        return new CommonResult(200, "赛程列表", scheduleService.getSchedules());
    }

    @GetMapping("schedule/{id}")
    public CommonResult getScheduleById(@PathVariable("id") int id) {
        Schedule s = scheduleService.getScheduleById(id);
        return s == null ?
                new CommonResult(401, "该赛程不存在")
                : new CommonResult(200, "查找到赛程信息", s);
    }
}
