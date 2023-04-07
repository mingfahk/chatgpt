package com.mingfahk.chatgpt.pojo.req;

import lombok.Data;

@Data
public class ChatRequestList {
    private String key;
    private String prompt;
    private String id;
    private Boolean is_continue;
}
