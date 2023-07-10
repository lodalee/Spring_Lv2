package com.example.spring_lv2_prac.service;

import com.example.spring_lv2_prac.dto.CommentRequestDto;
import com.example.spring_lv2_prac.dto.CommentResponseDto;
import com.example.spring_lv2_prac.entity.Board;
import com.example.spring_lv2_prac.entity.Comment;
import com.example.spring_lv2_prac.entity.User;
import com.example.spring_lv2_prac.repository.BoardRepository;
import com.example.spring_lv2_prac.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    //댓글 생성
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user){
        // board 가 있는지 확인
        // requestDto.getBoardId(); => board 찾아봐야하죠. => 찾아서 없으면 예외처리
        Board board = boardRepository.findById(requestDto.getBoardId()).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 Board 입니다.")
                );
        Comment comment = new Comment(requestDto, user.getUsername(), board);
        Comment newComment = commentRepository.save(comment);

        return new CommentResponseDto(newComment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user){
        //해당 comment가 DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않는 Comment 입니다.")
                );
        //comment의 유저네임이 user의 유저네임과 같은지
        if (!(comment.getUsername().equals(user.getUsername())) && !(user.getRole().getAuthority().equals("ROLE_ADMIN"))){
            throw new IllegalArgumentException("작성자와 관리자만 수정할 수 있습니다.");
        }
        //comment 내용을 수정하고 수정된 게시물을 클라이언트로
        comment.update(requestDto);
        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    public void deleteComment(Long id, User user){
        //해당 comment가 DB에 존재하는지 확인
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("존재하지 않는 Comment 입니다.")
        );
        //comment의 유저네임이 user의 유저네임과 같은지
        if (!(comment.getUsername().equals(user.getUsername())) && !(user.getRole().getAuthority().equals("ROLE_ADMIN"))){
            throw new IllegalArgumentException("작성자와 관리자만 삭제할 수 있습니다.");
        }
        //comment 삭제
        commentRepository.delete(comment);
    }
}
