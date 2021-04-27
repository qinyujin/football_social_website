package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.*;
import com.nefu.mvc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-21 12:19:00
 */
@RestController
@RequestMapping("/api/basic_info/")
public class BasicInfoManagerController {
    @Autowired
    private BasicInfoService basicInfoService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private NationService nationService;

    @Autowired
    private StyleService styleService;

    /* 四个基础信息的操作 */
    //队伍
    @PostMapping("team")
    public CommonResult saveTeam(@RequestBody Team team) {
        return new CommonResult(200, "添加队伍成功", teamService.saveTeam(team));
    }

    @DeleteMapping("team")
    public CommonResult deleteTeamById(@RequestParam("id") int id) {
        return teamService.deleteTeam(id) == 0 ?
                new CommonResult(401, "该队伍不存在")
                : new CommonResult(200, "删除队伍成功");
    }

    @PatchMapping("team")
    public CommonResult updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team) == 0 ?
                new CommonResult(401, "该队伍不存在")
                : new CommonResult(200, "更新队伍信息成功");
    }

    @GetMapping("teams")
    public CommonResult getTeams() {
        return new CommonResult(200, "队伍列表", teamService.getTeams());
    }

    @GetMapping("team/{id}")
    public CommonResult getTeamById(@PathVariable("id") int id) {
        Team team = teamService.getTeamById(id);
        return team == null ?
                new CommonResult(401, "该队伍不存在")
                : new CommonResult(200, "查找到队伍", team);
    }

    //球员
    @PostMapping("player")
    public CommonResult savePlayer(@RequestBody Player player) {
        return new CommonResult(200, "添加球员成功", playerService.savePlayer(player));
    }

    @DeleteMapping("player")
    public CommonResult deletePlayerById(@RequestParam("id") int id) {
        return playerService.deletePlayer(id) == 0 ?
                new CommonResult(401, "该球员不存在")
                : new CommonResult(200, "删除球员成功");
    }

    @PatchMapping("player")
    public CommonResult updatePlayer(@RequestBody Player player) {
        return playerService.updatePlayer(player) == 0 ?
                new CommonResult(401, "该球员不存在")
                : new CommonResult(200, "更新球员信息成功");
    }

    @GetMapping("players")
    public CommonResult getPlayers() {
        return new CommonResult(200, "球员列表", playerService.getPlayers());
    }

    @GetMapping("player/{id}")
    public CommonResult getPlayerById(@PathVariable("id") int id) {
        Player player = playerService.getPlayerById(id);
        return player == null ?
                new CommonResult(401, "该球员不存在")
                : new CommonResult(200, "查找到球员", player);
    }

    //国家
    @PostMapping("nation")
    public CommonResult saveNation(@RequestBody Nation nation) {
        return new CommonResult(200, "添加国家成功", nationService.saveNation(nation));
    }

    @DeleteMapping("nation")
    public CommonResult deleteNation(@RequestParam("id") int id){
        return nationService.deleteNation(id) == 0 ?
                new CommonResult(401,"该国家不存在")
                :new CommonResult(200,"删除国家成功");
    }

    @PatchMapping("nation")
    public CommonResult updateNation(@RequestBody Nation nation){
        return nationService.updateNation(nation) == 0 ?
                new CommonResult(401,"该国家不存在")
                :new CommonResult(200,"更新国家信息成功");
    }

    @GetMapping("nations")
    public CommonResult getNations(){
        return new CommonResult(200,"国家列表",nationService.getNations());
    }

    @GetMapping("nation/{id}")
    public CommonResult getNationById(@PathVariable("id") int id){
        Nation nation = nationService.getNationById(id);
        return nation == null ?
                new CommonResult(401,"该国家不存在")
                :new CommonResult(200,"查找到国家",nation);
    }

    //风格
    @PostMapping("style")
    public CommonResult saveStyle(@RequestBody Style style){
        return new CommonResult(200,"添加风格成功",styleService.saveStyle(style));
    }

    @DeleteMapping("style")
    public CommonResult deleteStyleById(@RequestParam("id") int id){
        return styleService.deleteStyle(id) == 0?
                new CommonResult(401,"该风格不存在")
                :new CommonResult(200,"删除风格成功");
    }

    @PatchMapping("style")
    public CommonResult updateStyle(@RequestBody Style style){
        return styleService.updateStyle(style) == 0 ?
                new CommonResult(401,"该风格不存在")
                :new CommonResult(200,"更新风格成功");
    }

    @GetMapping("styles")
    public CommonResult getStyles(){
        return new CommonResult(200,"风格列表",styleService.getStyles());
    }

    @GetMapping("style/{id}")
    public CommonResult getStyleById(@PathVariable("id") int id){
        Style style = styleService.getStyleById(id);
        return style == null ?
                new CommonResult(401,"该风格不存在")
                :new CommonResult(200,"查找到该风格",style);
    }

    /* basic_info表的操作。对外提供具体信息的接口表 */
    @PostMapping("basic_info")
    public CommonResult saveBasicInfo(@RequestBody BasicInfo basicInfo){
        return new CommonResult(200,"添加基础信息成功",basicInfoService.saveBasicInfo(basicInfo));
    }

    @DeleteMapping("basic_info")
    public CommonResult deleteBasicInfoById(@RequestParam("id") int id){
        return basicInfoService.deleteBasicInfo(id) == 0 ?
                new CommonResult(401,"该基础信息不存在")
                :new CommonResult(200,"删除成功");
    }

    @PatchMapping("basic_info")
    public CommonResult updateBasicInfo(@RequestBody BasicInfo basicInfo){
        return basicInfoService.updateBasicInfo(basicInfo) == 0 ?
                new CommonResult(401,"该基础信息不存在")
                :new CommonResult(200,"更新成功");
    }

    @GetMapping("basic_infos")
    public CommonResult getBasicInfos(){
        return new CommonResult(200,"基础信息列表",basicInfoService.getBasicInfos());
    }

    @GetMapping("basic_info/{id}")
    public CommonResult getBasicInfoById(@PathVariable("id") int id){
        BasicInfo basicInfo = basicInfoService.getBasicInfoById(id);
        return basicInfo==null?
                new CommonResult(401,"该基础信息不存在")
                :new CommonResult(200,"查找到基础信息",basicInfo);
    }
}
