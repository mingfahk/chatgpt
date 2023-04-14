package com.mingfahk.chatgpt.mapper;

import com.mingfahk.chatgpt.pojo.domain.OpenAiKeys;

import java.util.List;

/**
 * @Entity com.mingfahk.chatgpt.pojo.domain.OpenAiKeys
 */
public interface OpenAiKeysMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OpenAiKeys record);

    int insertSelective(OpenAiKeys record);

    OpenAiKeys selectByPrimaryKey(Long id);
    List<OpenAiKeys> selectAll();

    int updateByPrimaryKeySelective(OpenAiKeys record);

    int updateByPrimaryKey(OpenAiKeys record);

}
