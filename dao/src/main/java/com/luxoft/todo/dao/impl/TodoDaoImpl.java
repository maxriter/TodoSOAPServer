package com.luxoft.todo.dao.impl;


import com.luxoft.todo.api.FilterDto;
import com.luxoft.todo.api.model.TodoDto;
import com.luxoft.todo.dao.TodoDao;
import com.luxoft.todo.dao.entity.Todo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.luxoft.todo.dao.util.EntityConverter.convert;
import static com.luxoft.todo.dao.util.EntityConverter.convertTodoList;

@Repository
public class TodoDaoImpl implements TodoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TodoDto> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> query = cb.createQuery(Todo.class);
        Root<Todo> todoRoot = query.from(Todo.class);
        query.select(todoRoot);
        List<Todo> res = em.createQuery(query).getResultList();
        return convertTodoList(res);
    }

    public TodoDto get(Long id) {
        return convert(em.find(Todo.class, id));
    }

    @Override
    public TodoDto save(TodoDto todoDto) {
        Todo todo = convert(todoDto);
        todo.setDateTime(LocalDateTime.now());
        em.persist(todo);

        return convert(todo);
    }

    @Override
    public void remove(Long id) {
        Todo todo = em.find(Todo.class, id);
        em.remove(todo);
    }

    @Override
    public void update(TodoDto todoDto) {
        Todo todo = convert(get(todoDto.getId()));
        todo.setTodo(todoDto.getTodo());
        System.out.println("Update: " + todo);
        em.merge(todo);
    }

    @Override
    public List<TodoDto> searchByFilter(FilterDto filter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dateFrom = filter.getDateFrom().isEmpty() ? null : LocalDateTime.parse(filter.getDateFrom(), formatter);
        LocalDateTime dateTo = filter.getDateTo().isEmpty() ? null : LocalDateTime.parse(filter.getDateTo(), formatter);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> query = cb.createQuery(Todo.class);
        Root<Todo> todoRoot = query.from(Todo.class);
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getTodo() != null) {
            predicates.add(cb.like(todoRoot.get("todo"), "%" + filter.getTodo() + "%"));
        }
        if (dateFrom != null) {
            predicates.add(cb.greaterThanOrEqualTo(todoRoot.get("dateTime"), dateFrom));
        }
        if (dateTo != null) {
            predicates.add(cb.lessThanOrEqualTo(todoRoot.get("dateTime"), dateTo));
        }
        query.select(todoRoot)
                .where(predicates.toArray(new Predicate[]{}));
        List<Todo> res = em.createQuery(query).getResultList();
        System.out.println("Search by Filter in DAO: " + res);
        return convertTodoList(res);
    }
}
