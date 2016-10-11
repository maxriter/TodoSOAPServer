package com.luxoft.todo.dao.util;


import java.util.ArrayList;
import java.util.List;

import com.luxoft.todo.api.model.TodoDto;
import com.luxoft.todo.dao.entity.Todo;
import org.modelmapper.ModelMapper;

public class EntityConverter {

    static ModelMapper modelMapper = new ModelMapper();

    public static Todo convert(TodoDto todoDto) {
        return todoDto == null ? null: modelMapper.map(todoDto, Todo.class);
    }

    public static TodoDto convert(Todo todo) {
        return todo == null ? null: modelMapper.map(todo, TodoDto.class);
    }

    public static List<TodoDto> convertTodoList(List<Todo> todoList) {
        List<TodoDto> result = new ArrayList<>();
        for (Todo todo : todoList) {
            result.add(convert(todo));
        }
        return result;
    }

}
