package com.mingfahk.chatgpt.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "无key会话请求对象")
public class ChatNoKeyiRequest {
    @ApiModelProperty(value = "你的问题", example = "who are you")
    private String prompt;
    @ApiModelProperty(value = "你的ID，第一次请求可不填写", example = "1111111111111")
    private Long id;
    @ApiModelProperty(value = "是否持续对话", example = "true")
    private Boolean is_continue;
}
