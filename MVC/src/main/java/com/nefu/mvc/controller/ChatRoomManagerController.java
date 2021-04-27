package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.ChatRoom;
import com.nefu.mvc.entity.ChatRoomUser;
import com.nefu.mvc.entity.Message;
import com.nefu.mvc.entity.MyFile;
import com.nefu.mvc.service.ChatRoomService;
import com.nefu.mvc.service.ChatRoomUserService;
import com.nefu.mvc.service.FileService;
import com.nefu.mvc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author :覃玉锦
 * @create :2021-04-13 20:16:00
 */
@RestController
@RequestMapping("/api/chat_room/")
public class ChatRoomManagerController {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatRoomUserService chatRoomUserService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FileService fileService;

    //对聊天室的操作
    @PostMapping("chat_room")
    public CommonResult saveChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomService.saveChatRoom(chatRoom) == 0 ?
                new CommonResult(401, "请勿重复添加") :
                new CommonResult(200, "添加聊天室成功");
    }

    @DeleteMapping("chat_room")
    public CommonResult deleteChatRoom(@RequestParam("id") int id) {
        return chatRoomService.deleteChatRoom(id) == 0 ?
                new CommonResult(401, "该聊天室不存在")
                : new CommonResult(200, "删除聊天室成功");
    }

    @PatchMapping("chat_room")
    public CommonResult updateChatRoom(@RequestBody ChatRoom chatRoom) {
        return new CommonResult(200, "更新成功", chatRoomService.updateChatRoom(chatRoom));
    }

    @GetMapping("chat_rooms")
    public CommonResult getChatRooms() {
        return new CommonResult(200, "聊天室列表", chatRoomService.getChatRooms());
    }

    @GetMapping("chat_room/{id}")
    public CommonResult getChatRoomById(@PathVariable("id") int id) {
        ChatRoom cr = chatRoomService.getChatRoomById(id);
        return cr == null ?
                new CommonResult(401, "该聊天室不存在")
                : new CommonResult(200, "查找到聊天室", cr);
    }

    //用户对于聊天室的操作
    @PostMapping("user")
    public CommonResult saveChatRoomUser(@RequestBody ChatRoomUser chatRoomUser) {
        return new CommonResult(200, "用户成功加入该群", chatRoomUserService.saveChatRoomUser(chatRoomUser));
    }

    @DeleteMapping("user")
    public CommonResult deleteChatRoomUser(@RequestParam("id") int id) {
        return chatRoomUserService.deleteChatRoomUser(id) == 0 ?
                new CommonResult(401, "该用户已退出群聊")
                : new CommonResult(200, "用户退出群聊成功");
    }

    @PatchMapping("user")
    public CommonResult updateChatRoomUser(@RequestBody ChatRoomUser chatRoomUser) {
        return chatRoomUserService.updateChatRoomUser(chatRoomUser) == 0 ?
                new CommonResult(401, "该用户不在群聊")
                : new CommonResult(200, "更新成功");
    }

    //获取群聊中的用户id列表，需要提供chatroom的id
    @GetMapping("users/{id}")
    public CommonResult getChatRoomUsers(@PathVariable("id") int id) {
        return new CommonResult(200, "群中的用户列表", chatRoomUserService.getUidByRoomId(id));
    }

    @PostMapping("message")
    public CommonResult saveMessage(@RequestBody Message message) {
        return new CommonResult(200, "发送消息成功", messageService.saveMessage(message));
    }

    @DeleteMapping("message")
    public CommonResult cancelMessage(@RequestParam("id") int id) {
        int res = messageService.deleteMessage(id);
        return res == 1 ?
                new CommonResult(200, "撤回消息成功")
                : res == -1 ?
                new CommonResult(401, "超过时间不能撤回")
                : new CommonResult(401, "该消息不存在");
    }

    //文件，譬如图片、音频、视频、其他文件
    @PostMapping("file")
    public CommonResult saveFile(@RequestBody MyFile myFile){
        return new CommonResult(200,"添加文件类型数据成功",fileService.saveFile(myFile));
    }

    @DeleteMapping("file")
    public CommonResult deleteFile(@RequestParam("id") int id){
        return fileService.deleteFile(id) == 0 ?
                new CommonResult(401,"该文件不存在")
                :new CommonResult(200,"删除文件成功");
    }

    @PatchMapping("file")
    public CommonResult updateFile(@RequestBody MyFile myFile){
        return fileService.updateFile(myFile) == 0 ?
                new CommonResult(401,"该文件不存在")
                :new CommonResult(200,"更新文件信息成功");
    }

    @GetMapping("files")
    public CommonResult getFiles(){
        return new CommonResult(200,"文件列表",fileService.getFiles());
    }

    @GetMapping("file/{id}")
    public CommonResult getFileById(@PathVariable("id") int id){
        MyFile file = fileService.getFileById(id);
        return file == null ?
                new CommonResult(401,"该文件不存在")
                :new CommonResult(200,"查找到该文件",file);
    }
}
