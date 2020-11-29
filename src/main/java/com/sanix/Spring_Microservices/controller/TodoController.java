package com.sanix.Spring_Microservices.controller;

import com.sanix.Spring_Microservices.domain.ToDo;
import com.sanix.Spring_Microservices.exceptions.TodoNotFoundException;
import com.sanix.Spring_Microservices.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class TodoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/users/{name}/todos")
    public List<ToDo> retrieveTodos(@PathVariable String name){
        return toDoService.retrieveTodos(name);
    }

    @GetMapping("/users/{name}/todos/{id}")
    public EntityModel<ToDo> retrieveTodos(@PathVariable String name, @PathVariable int id){
        ToDo todo=toDoService.retrieveTodo(id);
        if(todo==null){
            throw new TodoNotFoundException("ToDo not found");
        }
        EntityModel<ToDo> toDoResource=EntityModel.of(todo);
        WebMvcLinkBuilder linkTo=WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveTodos(name));
        toDoResource.add(linkTo.withRel("parent"));
        return toDoResource;
    }

    @PostMapping("/users/{name}/todos")
    ResponseEntity<?> add(@PathVariable String name, @Valid @RequestBody ToDo todo){
        ToDo createdTodo=toDoService.addTodo(name, todo.getDesc(), todo.getTargetDate(), todo.isDone());
        if(createdTodo==null){
            return ResponseEntity.noContent().build();
        }
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
