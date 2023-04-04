package com.mingfahk.chatgpt.service;


import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public interface ChatService {

    ChatCompletionResponse getMsg(String prompt, String apiKey, String context) throws IOException, NoSuchAlgorithmException, KeyManagementException;
}
