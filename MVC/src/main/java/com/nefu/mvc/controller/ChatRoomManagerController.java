package com.nefu.mvc.controller;

import com.nefu.mvc.component.CommonResult;
import com.nefu.mvc.entity.ChatRoom;
import com.nefu.mvc.service.ChatRoomService;
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
}
