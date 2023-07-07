package com.example.spring_lv2_prac.controller;


import com.example.spring_lv2_prac.dto.BoardRequestDto;
import com.example.spring_lv2_prac.dto.BoardResponseDto;
import com.example.spring_lv2_prac.dto.MessageResponseDto;
import com.example.spring_lv2_prac.entity.User;
import com.example.spring_lv2_prac.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService; //

    // 게시글 생성하기 - 토큰 검증 필요함
    @PostMapping("")
    public BoardResponseDto creatBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return boardService.createBoard(requestDto, user);//
    }

    // 게시글 전체조회
    @GetMapping("")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();  //
    }

    // 게시글 선택조회
    @GetMapping("/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    // 게시글 수정하기 - 토큰 검증, 작성자 필터링 필요함
    @PutMapping("/{id}")

    public MessageResponseDto updateBoard(@PathVariable Long id,
                                          @RequestBody BoardRequestDto requestDto,
                                          HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        boardService.updateBoard(id, requestDto, user);

        return new MessageResponseDto("게시글 수정 성공");
    }

    // 메모 삭제하기 - 토큰 검증, 작성자 필터링 필요함
    @DeleteMapping("/{id}")
    public MessageResponseDto deleteMemo(@PathVariable Long id,
                                         HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        boardService.deleteMemo(id, user);

        return new MessageResponseDto("게시글 삭제 성공");  //
    }
}


