package com.karol172.lecturesmanager.dto;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AttachmentDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String urlAddress;

    @NotNull
    private Long lecture;

    public AttachmentDto () { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public Long getLecture() {
        return lecture;
    }

    public void setLecture(Long lecture) {
        this.lecture = lecture;
    }

}