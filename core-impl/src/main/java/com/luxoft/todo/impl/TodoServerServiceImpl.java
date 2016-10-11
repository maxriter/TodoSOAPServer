package com.luxoft.todo.impl;

import com.luxoft.todo.api.FilterDto;
import com.luxoft.todo.api.TodoServerService;
import com.luxoft.todo.api.model.TodoDto;
import com.luxoft.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TodoServerServiceImpl implements TodoServerService {

	@Autowired
	TodoDao todoDao;

	public List<TodoDto> getAll() {
		return todoDao.getAll();
	}

	public TodoDto save(TodoDto todoDto) {
		return todoDao.save(todoDto);
	}

	public void remove(Long id) {
		todoDao.remove(id);
	}

	public void update(TodoDto todoDto) {
		todoDao.update(todoDto);
	}

	public List<TodoDto> searchByFilter(FilterDto filter) {
		return todoDao.searchByFilter(filter);
	}

}
