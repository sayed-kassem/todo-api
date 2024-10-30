package com.todo.todo_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "todos")
@Data
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank( message = "Title is mandatory")
    private String title;

    private String description;

    @NotNull(message = "Due date is mandatory")
    private LocalDateTime dueDate;
    
    private boolean completed = false;
    
}
