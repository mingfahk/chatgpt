package com.mingfahk.chatgpt.common;

import com.mingfahk.chatgpt.pojo.bo.CacheQuestion;

import java.util.*;

public class APIKeys {
    // api列表，每次任务启动重新从数据库中拉取
    public static final Set<String> API_SET = new HashSet<>();

    /**
     * 存放临时问题，key是最开始访问的时间
     * value为最后一次访问的时间及对应问题集合
     * 每分钟清理十分钟内无继续问题的会话
     */
    public static final Map<Long, CacheQuestion> CONTEXT_MAP = new HashMap<>();
}
