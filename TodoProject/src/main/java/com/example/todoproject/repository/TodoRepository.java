package com.example.todoproject.repository;

import com.example.todoproject.model.MyUser;
import com.example.todoproject.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Integer> {
    Todo findTodoById(Integer id);
    List<Todo> findAllByMyUser(MyUser myUser);
}
