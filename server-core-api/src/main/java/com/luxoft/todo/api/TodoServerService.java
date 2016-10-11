package com.luxoft.todo.api;

import com.luxoft.todo.api.model.TodoDto;

import java.util.List;

public interface TodoServerService {

    List<TodoDto> getAll();

    TodoDto save(TodoDto todoDto);

    void remove(Long id);

    void update(TodoDto todoDto);

    List<TodoDto> searchByFilter(FilterDto filter);
}
