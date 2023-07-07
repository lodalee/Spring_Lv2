package com.example.spring_lv2_prac.repository;

import com.example.spring_lv2_prac.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    // 전체조회
    List<Board> findAllByOrderByModifiedAtDesc();  // Service의 전체조회 findAll()과 대체함
}

