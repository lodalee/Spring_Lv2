package com.example.spring_lv2_prac.dto;

import lombok.Getter;

@Getter
public class MessageResponseDto {
    private String msg;

    public MessageResponseDto(String msg) {
        this.msg = msg;
    }
}
//오류 뿐만 아니라 성공했을 때에도 메세지를 보내줘야 하기 때문에 살려둠.