package com.luxoft.todo.dao;

import com.luxoft.todo.api.FilterDto;
import com.luxoft.todo.api.model.TodoDto;

import java.util.List;

public interface TodoDao {

    List<TodoDto> getAll();

    TodoDto save(TodoDto todo);

    void remove(Long id);

    void update(TodoDto todoDto);

    List<TodoDto> searchByFilter(FilterDto filter);

}
