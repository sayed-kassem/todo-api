package com.todo.todo_app.controller;

import java.util.List;
import com.todo.todo_app.model.Todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.todo_app.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.findAll();
    }

    @GetMapping("/page")
    public Page<Todo> getPaginatedTodo(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "dueDate,asc") String[] sort) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sort[0]).ascending());
    return todoService.findAll(pageable);
}

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
            return todoService.findById(id)
            .map(todo -> ResponseEntity.ok(todo))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody Todo todo){
            Todo savedTodo = todoService.save(todo);
            return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @Valid @RequestBody Todo todoDetails){
            Todo updatedTodo = todoService.update(id, todoDetails);
            return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
         todoService.delete(id);
         return ResponseEntity.noContent().build();
    }

}
