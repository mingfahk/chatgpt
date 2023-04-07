package com.mingfahk.chatgpt.service.impl;

import com.mingfahk.chatgpt.pojo.req.ChatRequest;
import com.mingfahk.chatgpt.pojo.req.ChatRequestList;
import com.mingfahk.chatgpt.pojo.res.ChatResponse;
import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.mingfahk.chatgpt.common.APIKeys.API_SET;
import static com.mingfahk.chatgpt.common.APIKeys.CONTEXT_MAP;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Override
    public ChatCompletionResponse getMsg(ChatRequest chatRequest) {
        OpenAiClient openAiClient = buildClient(chatRequest.getKey());
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(chatRequest.toMsg()).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        return chatCompletionResponse;
    }

    @Override
    public ChatResponse chat(ChatRequestList chatRequest) {
        String id = chatRequest.getId() == null ? System.currentTimeMillis() + "" : chatRequest.getId();
        OpenAiClient openAiClient = buildClient(chatRequest.getKey());

        List<Message> msgList = new ArrayList<>();
        if (CONTEXT_MAP.keySet().contains(id)) {

        }

        // 如果是连续对话
        if (chatRequest.getIs_continue()) {
            List<String> contextList = CONTEXT_MAP.get(chatRequest.getId());
            // 加入上下文对象
            if (contextList != null && !contextList.isEmpty()) {
                contextList.forEach(context -> {
                    Message message = Message.builder().role(Message.Role.USER).content(context).build();
                    msgList.add(message);
                });
            }
        }
        // 加入当前问题
        msgList.add(Message.builder().role(Message.Role.USER).content(chatRequest.getPrompt()).build());
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content("").build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        chatCompletionResponse.setId(UUID.randomUUID().toString());
        return null;
    }

    private OpenAiClient buildClient(String key) {
        if (!API_SET.contains(key)) {
            API_SET.add(key);
            API_SET.forEach(System.out::println);
        }
        OpenAiClient openAiClient = OpenAiClient.builder()
                .apiKey(Arrays.asList(key))
                //自定义key的获取策略：默认KeyRandomStrategy
                .keyStrategy(new KeyRandomStrategy())
//                .keyStrategy(new FirstKeyStrategy())
                //自己做了代理就传代理地址，没有可不不传
//                .apiHost("https://localhost:7890/")
                .build();
        return openAiClient;
    }
}
