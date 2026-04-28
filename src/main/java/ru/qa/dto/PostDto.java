package ru.qa.dto;

import lombok.Data;

@Data
public class PostDto {
    private Integer id;
    public Integer userId;
    private String title;
    private String body;

}
