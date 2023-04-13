package com.mingfahk.chatgpt.pojo.res;

import com.unfbx.chatgpt.entity.common.Usage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("聊天返回对象")
@Data
public class ChatResponse {
    @ApiModelProperty(value = "你的ID")
    private Long id;
    @ApiModelProperty(value = "返回的内容")
    private String message;
    @ApiModelProperty(value = "此次API耗费的token数")
    private Usage usage;
}
