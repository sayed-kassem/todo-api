package com.todo.todo_app.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.todo.todo_app.model.Todo;
import com.todo.todo_app.repository.TodoRepository;
import java.util.Optional;
import java.util.List;


@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo save(Todo todo){
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> findById(Long id){
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Page<Todo> findAll(Pageable pageable){
        return todoRepository.findAll(pageable);
    }

    @Override 
    public Todo update(Long id, Todo todoDetails){
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Todo not found"));
        todo.setTitle(todoDetails.getTitle());
        todo.setDescription(todoDetails.getDescription());
        todo.setDueDate(todoDetails.getDueDate());
        todo.setCompleted(todoDetails.isCompleted());
        return todoRepository.save(todo);
    }

    @Override 
    public void delete(Long id){
        todoRepository.deleteById(id);
    }

}
