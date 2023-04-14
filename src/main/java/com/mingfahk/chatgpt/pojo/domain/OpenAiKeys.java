package com.mingfahk.chatgpt.pojo.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName open_ai_keys
 */
@Data
@NoArgsConstructor
public class OpenAiKeys implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * API-KEY
     */
    private String apiKey;

    public OpenAiKeys(String apiKey) {
        this.apiKey = apiKey;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OpenAiKeys other = (OpenAiKeys) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getApiKey() == null ? other.getApiKey() == null : this.getApiKey().equals(other.getApiKey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getApiKey() == null) ? 0 : getApiKey().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", apiKey=").append(apiKey);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}