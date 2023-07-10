package com.example.spring_lv2_prac.entity;

import com.example.spring_lv2_prac.dto.BoardRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "board")   // JPA 매핑할 테이블 이름
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // 글번호 식별자(자동)

    @Column(name = "username")
    private String username;                // 사용자 이름

    @Column(name = "title", nullable = false, length = 100)
    private String title;               // 입력한 제목

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;            // 작성 내용

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = {CascadeType.REMOVE})
    private List<Comment> commentList = new ArrayList<>();






    public Board(BoardRequestDto requestDto, String userName) {
        this.username = userName;
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    // 수정은 제목과 작성내용만 진행함
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
