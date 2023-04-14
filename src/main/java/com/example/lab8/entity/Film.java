package com.example.lab8.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILMS")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "filmName")
    private String name;

    @Column(name = "directorName")
    private String director;

    @Column(name = "filmGenre")
    private String genre;

    @Column(name = "filmYear")
    private int year;
}

