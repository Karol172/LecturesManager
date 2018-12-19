package com.karol172.lecturesmanager.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class UserDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String login;

    private String password;

    private String name;

    private Boolean isActive;

    @NotNull
    private String role;

    private Set<Long> conductedLectures;

    private Set<Long> listenedLectures;

    public UserDto() { }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Long> getConductedLectures() {
        return conductedLectures;
    }

    public void setConductedLectures(Set<Long> conductedLectures) {
        this.conductedLectures = conductedLectures;
    }

    public Set<Long> getListenedLectures() {
        return listenedLectures;
    }

    public void setListenedLectures(Set<Long> listenedLectures) {
        this.listenedLectures = listenedLectures;
    }

}