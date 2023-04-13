package com.mingfahk.chatgpt.service;


import com.mingfahk.chatgpt.pojo.req.ChatNoKeyiRequest;
import com.mingfahk.chatgpt.pojo.req.ChatRequest;
import com.mingfahk.chatgpt.pojo.req.ChatOpenAiRequest;
import com.mingfahk.chatgpt.pojo.res.ChatResponse;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface ChatService {

    ChatCompletionResponse getMsg(ChatRequest chatRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException;

    ChatResponse chat(ChatOpenAiRequest chatRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException;

    ChatResponse chatNoKey(ChatNoKeyiRequest chatRequest) throws IOException, NoSuchAlgorithmException, KeyManagementException;
}
