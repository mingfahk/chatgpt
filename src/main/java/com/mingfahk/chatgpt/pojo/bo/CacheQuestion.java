package com.mingfahk.chatgpt.pojo.bo;

import com.unfbx.chatgpt.entity.chat.Message;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 临时缓存类
 */
@Data
public class CacheQuestion {
    /**
     * 最后一个问题的时间
     */
    private LocalDateTime lastQuestion;
    /**
     * 问题集合
     */
    private List<Message> messages;
}
