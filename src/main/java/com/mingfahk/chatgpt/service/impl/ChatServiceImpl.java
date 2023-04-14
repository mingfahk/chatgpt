package com.mingfahk.chatgpt.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.mingfahk.chatgpt.mapper.OpenAiKeysMapper;
import com.mingfahk.chatgpt.pojo.bo.CacheQuestion;
import com.mingfahk.chatgpt.pojo.domain.OpenAiKeys;
import com.mingfahk.chatgpt.pojo.req.ChatNoKeyiRequest;
import com.mingfahk.chatgpt.pojo.req.ChatRequest;
import com.mingfahk.chatgpt.pojo.req.ChatOpenAiRequest;
import com.mingfahk.chatgpt.pojo.res.ChatResponse;
import com.mingfahk.chatgpt.service.ChatService;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.function.KeyRandomStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mingfahk.chatgpt.common.APIKeys.API_SET;
import static com.mingfahk.chatgpt.common.APIKeys.CONTEXT_MAP;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    @Autowired
    private OpenAiKeysMapper openAiKeysMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChatCompletionResponse getMsg(ChatRequest chatRequest) {
        String key = chatRequest.getKey();
        OpenAiClient openAiClient = buildClient(key);
        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(chatRequest.toMsg()).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Arrays.asList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        if (!API_SET.contains(key)) {
            API_SET.add(key);
            openAiKeysMapper.insert(new OpenAiKeys(key));
        }
        return chatCompletionResponse;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ChatResponse chat(ChatOpenAiRequest chatRequest) {
        String key = chatRequest.getKey();
        Long id = chatRequest.getId() == null ? System.currentTimeMillis() : chatRequest.getId();
        OpenAiClient openAiClient = buildClient(key);
        // 当前问题
        Message m = Message.builder().role(Message.Role.USER).content(chatRequest.getPrompt()).build();
        // 请求参数
        ChatCompletion chatCompletion;
        //响应结果
        ChatCompletionResponse chatCompletionResponse = null;
        // 本地缓存
        CacheQuestion cacheQuestion;
        // 如果是连续对话
        if (chatRequest.getIs_continue()) {
            // 获得该用户问题集合
            cacheQuestion = CONTEXT_MAP.get(id);
            // 如果不为空的话 加入上下文对象
            if (cacheQuestion != null) {
                // 加入此用户上下文对象
                List<Message> messages = cacheQuestion.getMessages();
                messages.add(m);
                chatCompletion = ChatCompletion.builder().messages(messages).build();
                chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
            } else {
                cacheQuestion = new CacheQuestion();
                List<Message> messages = new ArrayList<>();
                messages.add(m);
                cacheQuestion.setMessages(messages);
                chatCompletion = ChatCompletion.builder().messages(messages).build();
                chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
                CONTEXT_MAP.put(id, cacheQuestion);
            }
            cacheQuestion.setLastQuestion(LocalDateTime.now());
        } else {
            // 加入此用户上下文对象
            cacheQuestion = new CacheQuestion();
            List<Message> messages = new ArrayList<>();
            messages.add(m);
            cacheQuestion.setMessages(messages);
            chatCompletion = ChatCompletion.builder().messages(messages).build();
            chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
        }
        if (!API_SET.contains(key)) {
            API_SET.add(key);
            openAiKeysMapper.insert(new OpenAiKeys(key));
        }
        ChatResponse chatResponse = new ChatResponse();
        chatResponse.setId(id);
        chatResponse.setMessage(chatCompletionResponse.getChoices().get(0).getMessage().getContent());
        chatResponse.setUsage(chatCompletionResponse.getUsage());
        return chatResponse;
    }

    @Override
    public ChatResponse chatNoKey(ChatNoKeyiRequest chatRequest) {
        ChatOpenAiRequest chatOpenAiRequest = new ChatOpenAiRequest();
        BeanUtils.copyProperties(chatRequest, chatOpenAiRequest);
        chatOpenAiRequest.setKey(RandomUtil.randomEle(new ArrayList<>(API_SET)));
        return chat(chatOpenAiRequest);
    }

    private OpenAiClient buildClient(String key) {

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
