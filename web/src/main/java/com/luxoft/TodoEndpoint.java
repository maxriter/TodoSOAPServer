package com.luxoft;

import com.luxoft.converters.Converter;
import com.luxoft.converters.ListConverter;
import com.luxoft.soap.*;
import com.luxoft.todo.api.TodoServerService;
import com.luxoft.todo.api.model.TodoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@EnableTransactionManagement
public class TodoEndpoint {

    private static final String NAMESPACE_URI = "http://luxoft.com/soap";
    @Autowired
    private TodoServerService service;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "todoRequest")
    @ResponsePayload
    public GetTodoResponse getAllTodo(@RequestPayload TodoRequest request) {
        return getAllTodo();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeRequest")
    @ResponsePayload
    public GetTodoResponse remove(@RequestPayload RemoveRequest request) {
        service.remove(Long.valueOf(request.getId()));
        return getAllTodo();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addTodo")
    @ResponsePayload
    public AddTodoResponse add(@RequestPayload AddTodo request) {
        TodoDto todoDto = new TodoDto();
        todoDto.setTodo(request.getTodo());
        AddTodoResponse response = new AddTodoResponse();
        response.setTodo(Converter.convertTodoDtoToLongDateTodo(service.save(todoDto)));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTodo")
    @ResponsePayload
    public GetTodoResponse update(@RequestPayload UpdateTodo request) {
        service.update(Converter.convertTodoToLocalDateTimeDateDto(request.getTodo()));
        return getAllTodo();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "filterTodo")
    @ResponsePayload
    public FilterTodoResponse filter(@RequestPayload FilterTodo filterTodo) {
        FilterTodoResponse response = new FilterTodoResponse();
        List<TodoDto> listTodo = service.searchByFilter(Converter.convertToFilterDto(filterTodo));
        response.getFilteredTodo().addAll(ListConverter.convertToTodoList(listTodo));
        return response;
    }

    private GetTodoResponse getAllTodo() {
        GetTodoResponse response = new GetTodoResponse();
        response.getAllTodo().addAll(ListConverter.convertToTodoList(service.getAll()));
        return response;
    }


}
