package com.mingfahk.chatgpt.pojo.req;

import lombok.Data;

@Data
public class ChatRequest {
    private String key;
    private String prompt;
    private String context;


    public String toMsg() {
        if (context == null) {
            return prompt;
        }
        return context + "\n" + prompt;
    }
}
