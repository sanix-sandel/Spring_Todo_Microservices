package com.sanix.Spring_Microservices.service;

import com.sanix.Spring_Microservices.domain.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToDoService {

    private static List<ToDo> todos=new ArrayList<ToDo>();
    private static int todoCount=3;

    static {
        todos.add(new ToDo(1, "Jack", "Learn Spring MVC", new Date(),false));
        todos.add(new ToDo(2, "Jack", "Learn Struts", new Date(),false));
        todos.add(new ToDo(3, "Jill", "Learn Hibernate", new Date(),false));
    }

    public List<ToDo> retrieveTodos(String user){
        List<ToDo> filteredTodos=new ArrayList<ToDo>();
        for(ToDo todo:todos){
            if(todo.getUser().equals(user))
                filteredTodos.add(todo);
        }
        return filteredTodos;
    }

    public ToDo addTodo(String name, String desc, Date targetDate, boolean isDone){
        ToDo todo=new ToDo(++todoCount, name, desc, targetDate, isDone);
        todos.add(todo);
        return todo;
    }

    public ToDo retrieveTodo(int id){
        for(ToDo todo:todos){
            if(todo.getId()==id)
                return todo;
        }
        return null;
    }
}
