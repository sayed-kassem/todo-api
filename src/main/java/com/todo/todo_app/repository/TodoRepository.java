package com.todo.todo_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.todo_app.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>  {
    
}
