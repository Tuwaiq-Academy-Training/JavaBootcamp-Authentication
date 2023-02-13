package com.example.todoproject.controller;

import com.example.todoproject.dto.ApiResponse;
import com.example.todoproject.model.MyUser;
import com.example.todoproject.model.Todo;
import com.example.todoproject.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
public class TodoController {

    private final TodoService todoService;

    // Admin
    @GetMapping("/all-todos")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(todoService.getAllTodo());
    }

    // User
    @GetMapping("/my-todos")
    public ResponseEntity getMyTodos(@AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getMyTodos(myUser.getId()));
    }

    // User
    @GetMapping("/{id}")
    public ResponseEntity getTodoById(@PathVariable Integer id , @AuthenticationPrincipal MyUser myUser){
        return ResponseEntity.status(200).body(todoService.getTodoById(id , myUser.getId()));
    }

    // User
    @PostMapping("/add-todo")
    public ResponseEntity addTodo(@RequestBody @Valid Todo todo, @AuthenticationPrincipal MyUser myUser){
        todoService.addTodo(todo,myUser.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Todo Added"));
    }

    // User
    @PutMapping("/update/{id}")
    public ResponseEntity updateTodo(@RequestBody @Valid Todo todo, @PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        todoService.updateTodo(id,todo,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Todo Updated"));
    }

    // User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Integer id, @AuthenticationPrincipal MyUser myUser){
        todoService.deleteTodo(id,myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Todo deleted"));
    }

}
