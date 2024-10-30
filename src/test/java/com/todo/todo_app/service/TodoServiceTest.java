package com.todo.todo_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

import com.todo.todo_app.model.Todo;
import com.todo.todo_app.repository.TodoRepository;

public class TodoServiceTest {
    
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTodo(){
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDueDate(LocalDateTime.now());
        
        when(todoRepository.save(todo)).thenReturn(todo);

        Todo savedTodo = todoService.save(todo);
        assertNotNull(savedTodo);
        assertEquals("Test Todo", savedTodo.getTitle());
        verify(todoRepository, times(1)).save(todo);


    }

    @Test
    void testFindTodoById() {
        Todo todo = new Todo();

        todo.setId(1L);
        todo.setTitle("Test Todo");

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> foundTodo = todoService.findById(1L);
        assertTrue(foundTodo.isPresent());
        assertEquals("Test Todo", foundTodo.get().getTitle());
        verify(todoRepository,times(1)).findById(1L);
    }
    @Test
    void testDeleteTodo() {
        Long todoId = 1L;
        doNothing().when(todoRepository).deleteById(todoId);

        todoService.delete(todoId);
        verify(todoRepository,times(1)).deleteById(todoId);
    }
}
