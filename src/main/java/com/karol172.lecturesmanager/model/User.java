package com.karol172.lecturesmanager.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
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

    @ManyToMany(mappedBy = "speakers", fetch = FetchType.EAGER)
    private Set<Lecture> conductedLectures;

    @ManyToMany(mappedBy = "presentLiseners")
    private Set<Lecture> listenedLectures;

    public User() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Lecture> getConductedLectures() {
        return conductedLectures;
    }

    public void setConductedLectures(Set<Lecture> conductedLectures) {
        this.conductedLectures = conductedLectures;
    }

    public Set<Lecture> getListenedLectures() {
        return listenedLectures;
    }

    public void setListenedLectures(Set<Lecture> listenedLectures) {
        this.listenedLectures = listenedLectures;
    }

    public void addConductedLecture (Lecture lecture) {
        if (lecture != null && !conductedLectures.contains(lecture))
            lecture.getSpeakers().add(this);
    }

    public void removeConductedLecture (Lecture lecture) {
        if (lecture != null && conductedLectures.contains(lecture))
            lecture.getSpeakers().remove(this);
    }

    public void addListenedLecture (Lecture lecture) {
        if (lecture != null && !listenedLectures.contains(lecture))
            lecture.getPresentLiseners().add(this);
    }

    public void removeListenedLecture (Lecture lecture) {
        if (lecture != null && listenedLectures.contains(lecture))
            lecture.getPresentLiseners().remove(this);
    }

}