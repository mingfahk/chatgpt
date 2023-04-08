package com.mingfahk.chatgpt.common;

import com.mingfahk.chatgpt.pojo.bo.CacheQuestion;

import java.util.*;

public class APIKeys {
    public static final Set<String> API_SET = new HashSet<>();

    /**
     * 存放临时问题，key是最开始访问的时间
     * value为最后一次访问的时间及对应问题集合
     */
    public static final Map<String, CacheQuestion> CONTEXT_MAP = new HashMap<>();
}
