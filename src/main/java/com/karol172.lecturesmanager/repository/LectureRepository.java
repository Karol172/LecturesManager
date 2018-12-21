package com.karol172.lecturesmanager.repository;

import com.karol172.lecturesmanager.model.Lecture;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByDateAfterOrderByDateDesc (LocalDateTime now);
}