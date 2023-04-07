package com.mingfahk.chatgpt.controller;

import com.mingfahk.chatgpt.pojo.req.ChatRequest;
import com.mingfahk.chatgpt.pojo.req.ChatRequestList;
import com.mingfahk.chatgpt.pojo.res.ChatResponse;
import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/chatgpt")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("getMsg")
    public ChatCompletionResponse getMsg(@RequestBody ChatRequest chatRequest) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.getMsg(chatRequest);
    }

    @GetMapping("chat")
    public ChatResponse chat(@RequestBody ChatRequestList chatRequest) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.chat(chatRequest);
    }
}
