package com.cuaderno.board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private String priority;
    private Long userId;
    private Long boardId;
}