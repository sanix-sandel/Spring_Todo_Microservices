package com.sanix.Spring_Microservices.controller;

import com.sanix.Spring_Microservices.domain.ToDo;
import com.sanix.Spring_Microservices.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/users/{name}/todos")
    public List<ToDo> retrieveTodos(@PathVariable String name){
        return toDoService.retrieveTodos(name);
    }

    @GetMapping("/users/{name}/todos/{id}")
    public ToDo retrieveTodos(@PathVariable String name, @PathVariable int id){
        return toDoService.retrieveTodo(id);
    }

    @PostMapping("/users/{name}/todos")
    ResponseEntity<?> add(@PathVariable String name, @RequestBody ToDo todo){
        ToDo createdTodo=toDoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), todo.isDone());
        if(createdTodo==null){
            return ResponseEntity.noContent().build();
        }
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
