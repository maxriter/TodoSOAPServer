package com.luxoft.converters;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.luxoft.soap.FilterTodo;
import com.luxoft.soap.Todo;
import com.luxoft.todo.api.FilterDto;
import com.luxoft.todo.api.model.TodoDto;

public final class Converter {
	public static TodoDto convertTodoToLocalDateTimeDateDto(Todo todo) {
		TodoDto convertedDto = new TodoDto();
		convertedDto.setId(todo.getId());
		convertedDto.setTodo(todo.getTodo());
		convertedDto.setDateTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(todo.getDateTime()), ZoneId.systemDefault()));
		return convertedDto;
	}

	public static Todo convertTodoDtoToLongDateTodo(TodoDto todoDto) {
		Todo convertedDto = new Todo();
		convertedDto.setId(todoDto.getId());
		convertedDto.setTodo(todoDto.getTodo());
		if (todoDto.getDateTime() != null) {
			convertedDto.setDateTime(todoDto.getDateTime().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli());
		}
		return convertedDto;
	}

	public static FilterDto convertToFilterDto(FilterTodo filterTodo) {
		FilterDto filterDto = new FilterDto();
		filterDto.setTodo(filterTodo.getTodo());
		filterDto.setDateFrom(filterTodo.getDateFrom());
		filterDto.setDateTo(filterTodo.getDateTo());
		return filterDto;
	}

}
