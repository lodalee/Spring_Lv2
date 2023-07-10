package com.example.spring_lv2_prac.service;

import com.example.spring_lv2_prac.dto.BoardRequestDto;
import com.example.spring_lv2_prac.dto.BoardResponseDto;
import com.example.spring_lv2_prac.entity.Board;
import com.example.spring_lv2_prac.entity.User;
import com.example.spring_lv2_prac.repository.BoardRepository;
import com.example.spring_lv2_prac.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
//    private final CommentRepository commentRepository;


    // 생성 필터에서 토큰을 검사해 가져오기 때문에 따로 구현x
    public BoardResponseDto createBoard(BoardRequestDto requestDto, User user) {
        Board board = new Board(requestDto, user.getUsername());  // 상황에 맞는 생성자 지정함
        // DB저장
        Board saveBoard = boardRepository.save(board);
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }


    // 전체조회
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }


    // 선택조회
    public BoardResponseDto getBoardById(Long id) {
        Board board = findBoard(id);
        return new BoardResponseDto(board);
    }

    // 수정
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, User user) {
        // 해당 게시글이 DB에 존재하는지 확인
        Board board = findBoard(id);
        if (!(board.getUsername().equals(user.getUsername()))){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        }
        // board 내용 수정 제목, 작성 내용을 수정하고 수정된 게시물 클라이언트로
        board.update(requestDto);
        return new BoardResponseDto(board);
    }

    // 삭제
    @Transactional
    public void deleteMemo(Long id, User user) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = findBoard(id);
        if (!(board.getUsername().equals(user.getUsername()))){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

//        //board 에 달린 댓글 조회
//        for (Comment comment : board.getCommentList()){
//            System.out.println("comment.getName() = " + comment.getComment());
//        }
//
//        //해당 board 댓글 데이터 삭제
//        commentRepository.deleteAll(board.getCommentList());

        // board 삭제
        boardRepository.delete(board);
    }

    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
