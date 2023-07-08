package com.example.spring_lv2_prac.controller;

import com.example.spring_lv2_prac.dto.CommentRequestDto;
import com.example.spring_lv2_prac.dto.CommentResponseDto;
import com.example.spring_lv2_prac.dto.MessageResponseDto;
import com.example.spring_lv2_prac.entity.User;
import com.example.spring_lv2_prac.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    //댓글 작성하기 - 토큰 검증 필요
    @PostMapping("")
    public CommentResponseDto createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        System.out.println(request.getAttribute("user"));
        return commentService.createComment(requestDto, user);
    }

    //선택한 댓글 수정하기 - 토큰 검증 필요, 작성자 필터링
    @PutMapping("/{id}") //commentId
    public MessageResponseDto updateComment(@PathVariable Long id,
                                            @RequestBody CommentRequestDto requestDto,
                                            HttpServletRequest request){
        User user = (User)request.getAttribute("user"); //User로 적어서 user가 확인되지 않았다.
        commentService.updateComment(id, requestDto, user);

        return new MessageResponseDto("댓글 수정 성공");
    }

    //선택한 댓글 삭제하기
    @DeleteMapping("/{id}") //commentId
    public MessageResponseDto deleteComment(@PathVariable Long id,
                                            HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        commentService.deleteComment(id, user);

        return new MessageResponseDto("댓글 삭제 성공");
    }
}
