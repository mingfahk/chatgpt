package com.mingfahk.chatgpt.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "简单聊天请求对象")
public class ChatRequest {
    @ApiModelProperty(value = "你的api key", example = "sk-xxxxxxxxxxxxx")
    private String key;
    @ApiModelProperty(value = "你的问题", example = "who are you")
    private String prompt;
    @ApiModelProperty(value = "上下文对象，可忽略")
    private String context;


    public String toMsg() {
        if (context == null) {
            return prompt;
        }
        return context + "\n" + prompt;
    }
}
