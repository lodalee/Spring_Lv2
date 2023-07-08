package com.example.spring_lv2_prac.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long boardId; // board의 아이디 누구에게 속해야 하는지 알아야 하기 때문
    private String comment;

}
