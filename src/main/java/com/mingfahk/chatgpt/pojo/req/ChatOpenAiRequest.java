package com.mingfahk.chatgpt.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "动态聊天请求对象")
public class ChatOpenAiRequest {
    @ApiModelProperty(value = "你的api key", example = "sk-xxxxxxxxxxxxx")
    private String key;
    @ApiModelProperty(value = "你的问题", example = "who are you")
    private String prompt;
    @ApiModelProperty(value = "你的ID，第一次请求不填写，后续请求使用返回的ID，可保留上下文", example = "1111111111111")
    private Long id;
    @ApiModelProperty(value = "是否持续对话", example = "true")
    private Boolean is_continue;
}
