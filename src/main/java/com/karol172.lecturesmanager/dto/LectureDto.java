package com.karol172.lecturesmanager.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

public class LectureDto implements Serializable {

    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    private String title;

    private String description;

    @NotNull
    @NotEmpty
    private LocalDateTime date;

    private Set<Long> attachments;

    private Set<Long> speakers;

    private Set<Long> presentLiseners;

    public LectureDto() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Set<Long> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Long> attachments) {
        this.attachments = attachments;
    }

    public Set<Long> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<Long> speakers) {
        this.speakers = speakers;
    }

    public Set<Long> getPresentLiseners() {
        return presentLiseners;
    }

    public void setPresentLiseners(Set<Long> presentLiseners) {
        this.presentLiseners = presentLiseners;
    }

}