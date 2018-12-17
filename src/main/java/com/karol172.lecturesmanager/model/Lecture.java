package com.karol172.lecturesmanager.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "LECTURE")
public class Lecture {

    @Id
    @SequenceGenerator(name = "LECTURE_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LECTURE_SEQ_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "DATE", nullable = false)
    private LocalDateTime date;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private Set<Attachment> attachments;

    @ManyToMany(mappedBy = "conductedLectures", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<User> speakers;

    @ManyToMany(mappedBy = "listenedLectures", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    private Set<User> presentLiseners;

    public Lecture() { }

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

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Set<User> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Set<User> speakers) {
        this.speakers = speakers;
    }

    public Set<User> getPresentLiseners() {
        return presentLiseners;
    }

    public void setPresentLiseners(Set<User> presentLiseners) {
        this.presentLiseners = presentLiseners;
    }

}