package com.tanerdundar.share5.api.dto;

import com.tanerdundar.share5.entities.Statu;
import lombok.Data;

@Data
public class GetOnePostByPostId {
    private String content;
    private Statu postStatu;
    private int numberOfLikes;
    private long userId;

}
