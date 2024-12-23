package com.cuaderno.board.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(value = 0, message = "El número de tareas no puede ser negativo")
    private int taskCount;

    @Min(value = 0, message = "El número de tareas completadas no puede ser negativo")
    private int completedTasks;

    @ElementCollection
    @CollectionTable(name = "board_tasks", joinColumns = @JoinColumn(name = "board_id"))
    @Column(name = "task_id")
    private List<Long> taskIds;
}