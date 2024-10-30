package com.todo.todo_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.todo.todo_app.model.Todo;

public interface TodoService {
    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    List<Todo> findAll();
    Page<Todo> findAll(Pageable pageable);
    Todo update(Long id, Todo todoDetails);
    void delete(Long id);
}
