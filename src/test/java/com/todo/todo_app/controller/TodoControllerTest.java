package com.todo.todo_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todo_app.model.*;
import com.todo.todo_app.service.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle("New Task");
        todo.setDueDate(LocalDateTime.now());

        when(todoService.save(Mockito.any(Todo.class))).thenReturn(todo);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Task"));
    }

    @Test
    void testGetTodoById() throws Exception {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test Task");

        when(todoService.findById(1L)).thenReturn(Optional.of(todo));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"));
    }
}
