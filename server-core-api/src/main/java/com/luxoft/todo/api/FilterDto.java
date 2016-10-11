package com.luxoft.todo.api;

import java.util.Objects;

public class FilterDto {

    Long id;
    String todo;
    String dateFrom;
    String dateTo;

    public FilterDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FilterDto filterDto = (FilterDto) o;
        return Objects.equals(id, filterDto.id) &&
            Objects.equals(todo, filterDto.todo) &&
            Objects.equals(dateFrom, filterDto.dateFrom) &&
            Objects.equals(dateTo, filterDto.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, todo, dateFrom, dateTo);
    }
}
