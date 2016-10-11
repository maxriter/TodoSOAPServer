package com.luxoft.todo.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "NOTES")

@Data
public class Todo {

    @Id
    @Column(name = "TODO_ID")
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    @Column(name = "TODO")
    private String todo;

    @Column(name="DATE_TIME")
    private LocalDateTime dateTime;

    public Todo() {
    }

}

