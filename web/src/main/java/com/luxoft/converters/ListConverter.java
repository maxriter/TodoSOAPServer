package com.luxoft.converters;

import java.util.ArrayList;
import java.util.List;

import com.luxoft.soap.Todo;
import com.luxoft.todo.api.model.TodoDto;

public class ListConverter {

	public static List<Todo> convertToTodoList(List<TodoDto> list) {
		List<Todo> result = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			result.add(Converter.convertTodoDtoToLongDateTodo(list.get(i)));
		}
		return result;
	}
}
