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

    public Lecture() { }
}
