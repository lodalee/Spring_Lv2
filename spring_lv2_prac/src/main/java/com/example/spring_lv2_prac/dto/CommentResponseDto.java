package com.example.spring_lv2_prac.dto;

import com.example.spring_lv2_prac.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class CommentResponseDto {
    private String username; // 작성자 이름
    private String comment; // 댓글 내용
    private LocalDateTime createdAt; // 작성 시간

    public CommentResponseDto(String username, String comment, LocalDateTime createdAt) {
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public CommentResponseDto(Comment newComment) {
        this.username = newComment.getUsername();
        this.comment = newComment.getComment();
        this.createdAt = newComment.getCreatedAt();
    }
}
