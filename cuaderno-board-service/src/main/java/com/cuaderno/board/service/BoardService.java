package com.cuaderno.board.service;


import com.cuaderno.board.client.TaskClient;
import com.cuaderno.board.dto.TaskDTO;
import com.cuaderno.board.entity.Board;
import com.cuaderno.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {


    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TaskClient taskClient;

    public Board getBoardWithTasks(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new RuntimeException("Board not found"));
        List<Long> taskIds = board.getTaskIds();

        // Llamar al cuaderno-task-service para obtener las tareas
        List<TaskDTO> tasks = taskClient.getTasksByIds(taskIds);

        return board;
    }


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