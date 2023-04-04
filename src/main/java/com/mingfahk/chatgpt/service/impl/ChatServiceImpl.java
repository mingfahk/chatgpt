package com.mingfahk.chatgpt.service.impl;

import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Override
    public ChatCompletionResponse getMsg(String prompt, String apiKey, String context) {
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(Arrays.asList(apiKey))
                //自定义key的获取策略：默认KeyRandomStrategy
                .keyStrategy(new KeyRandomStrategy())
//                .keyStrategy(new FirstKeyStrategy())
                //自己做了代理就传代理地址，没有可不不传
//                .apiHost("https://自己代理的服务器地址/")
                .build();
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(prompt).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        return chatCompletionResponse;
    }
}
