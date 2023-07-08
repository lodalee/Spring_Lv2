package com.example.spring_lv2_prac.repository;

import com.example.spring_lv2_prac.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByOrderByModifiedAtDesc(); // findAll 대체
}
