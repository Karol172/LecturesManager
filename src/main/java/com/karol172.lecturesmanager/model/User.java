package com.karol172.lecturesmanager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @SequenceGenerator(name = "USER_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN", unique = false, updatable = false, nullable = false)
    private String login;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "ROLE", nullable = false)
    @Enumerated
    private Role role;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(name = "SPEAKERS", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE_ID", referencedColumnName = "ID"))
    private Set<Lecture> conductedLectures;

    @ManyToMany(cascade = {CascadeType.DETACH})
    @JoinTable(name = "PRESENCE", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "LECTURE_ID", referencedColumnName = "ID"))
    private Set<Lecture> listenedLectures;

    public User() { }
}
