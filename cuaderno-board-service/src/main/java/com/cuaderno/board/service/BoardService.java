package com.cuaderno.board.service;


import com.cuaderno.board.entity.Board;
import com.cuaderno.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;


    public List<Board> findAll() {
        return boardRepository.findAll();
    }


    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }


    public Board save(Board board) {
        return boardRepository.save(board);
    }


    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}