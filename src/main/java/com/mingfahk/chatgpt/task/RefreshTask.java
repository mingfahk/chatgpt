package com.mingfahk.chatgpt.task;

import com.mingfahk.chatgpt.common.APIKeys;
import com.mingfahk.chatgpt.mapper.OpenAiKeysMapper;
import com.mingfahk.chatgpt.pojo.domain.OpenAiKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class RefreshTask {

    @Autowired
    private OpenAiKeysMapper openAiKeysMapper;

    // 初始化库里的key
    @PostConstruct
    public void freshApiKey() {
        List<OpenAiKeys> openAiKeys = openAiKeysMapper.selectAll();
        APIKeys.API_SET.addAll(openAiKeys.stream().map(OpenAiKeys::getApiKey).collect(Collectors.toList()));
    }

    // 每分钟清理10分钟内未响应的上下文
    @Scheduled(cron = "0 * * * * *")
    public void freshContext() {
        APIKeys.CONTEXT_MAP.entrySet().removeIf(k -> k.getValue().getLastQuestion().plusMinutes(10).isBefore(LocalDateTime.now()));
    }
}
