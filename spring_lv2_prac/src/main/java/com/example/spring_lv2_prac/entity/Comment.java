package com.example.spring_lv2_prac.entity;

import com.example.spring_lv2_prac.dto.CommentRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")   // JPA 매핑할 테이블 이름
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;  //댓글 번호 식별자

    @Column
    private String username; //사용자 이름

    @Column(nullable = false, length = 500)
    private  String comment; //댓글 작성 내용

    @JsonIgnore // 순회 조회를 막기 위한 JSOn 파싱할 떄 제외를 시킨다.
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(CommentRequestDto requestDto, String username, Board board) {
        this.username = username;
        this.comment = requestDto.getComment();
        this.board = board;
    }

    public void update(CommentRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}
