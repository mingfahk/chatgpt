package com.mingfahk.chatgpt.controller;

import com.mingfahk.chatgpt.pojo.req.ChatNoKeyiRequest;
import com.mingfahk.chatgpt.pojo.req.ChatRequest;
import com.mingfahk.chatgpt.pojo.req.ChatOpenAiRequest;
import com.mingfahk.chatgpt.pojo.res.ChatResponse;
import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Api(value = "聊天API调用", tags = "聊天API调用")
@RestController
@RequestMapping("/chatgpt")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @ApiOperation(value = "单条聊天")
    @PostMapping("getMsg")
    public ChatCompletionResponse getMsg(@RequestBody ChatRequest chatRequest) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.getMsg(chatRequest);
    }

    @ApiOperation(value = "可选择持续聊天")
    @PostMapping("chat")
    public ChatResponse chat(@RequestBody ChatOpenAiRequest chatOpenAiRequest) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.chat(chatOpenAiRequest);
    }

    @ApiOperation(value = "无key聊天")
    @PostMapping("chatNoKey")
    public ChatResponse chatNoKey(@RequestBody ChatNoKeyiRequest chatOpenAiRequest) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.chatNoKey(chatOpenAiRequest);
    }
}
