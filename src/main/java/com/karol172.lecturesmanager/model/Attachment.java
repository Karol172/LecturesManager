package com.karol172.lecturesmanager.model;

import javax.persistence.*;

@Entity
@Table(name = "ATTACHMENT")
public class Attachment {

    @Id
    @SequenceGenerator(name = "ATTACHMENT_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTACHMENT_SEQ_GEN")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;

    @Column(name = "URL_ADDRESS", nullable = false, updatable = false)
    private String urlAddress;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LECTURE_ID", updatable = false)
    private Lecture lecture;

    public Attachment() { }
}
