package com.mingfahk.chatgpt.controller;

import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ChatCompletionResponse getMsg(String prompt, String key, String context) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        return chatService.getMsg(prompt, key, context);
    }
}
