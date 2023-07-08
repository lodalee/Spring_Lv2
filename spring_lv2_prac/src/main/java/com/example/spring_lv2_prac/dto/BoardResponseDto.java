package com.example.spring_lv2_prac.dto;

import com.example.spring_lv2_prac.entity.Board;
import com.example.spring_lv2_prac.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto  {
    private String username;                // 입력한 이름
    private String title;               // 입력한 제목
    private String contents;            // 작성 내용
    private LocalDateTime createdAt;         // 작성된 시간(자동)

    public BoardResponseDto(Board board) {
        this.username = board.getUsername();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
    }
}

